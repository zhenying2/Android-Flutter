package com.example.nolfi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private int userStatus;                     // store(0) vs customer(1)
    private FirebaseAuth mFirebaseAuth;         // 파이어베이스 인증
    private DatabaseReference mDatabaseRef;     // 실시간 데이터베이스
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private RadioGroup rgUserInfo;              // store vs customer
    private EditText mEtEmail, mEtPwd;          // 회원가입 입력필드
    private EditText mEtNickname, mEtStoreAddress, mEtStoreCategory;
    private Button mBtnRegister, mBtnImage;     // 회원가입 버튼
    private View viewAddress, viewCategory;

    ImageView imageViewProfile;
    Uri selectedImageURi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userStatus=2;
        mFirebaseAuth = mFirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("NolFI");
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        rgUserInfo=findViewById(R.id.radioGroupUserInfo);
        mEtEmail=findViewById(R.id.registerEmail);
        mEtPwd=findViewById(R.id.registerPassword);
        mBtnRegister=findViewById(R.id.registerSignUpBtn);

        //mBtnImage=findViewById(R.id.register_image_btn);
        mEtNickname=findViewById(R.id.registerNickname);
        mEtStoreAddress=findViewById(R.id.registerStoreAddress);
        mEtStoreCategory=findViewById(R.id.registerStoreCategory);

        imageViewProfile=findViewById(R.id.iv_register_profile);
        viewAddress=findViewById(R.id.viewAddress);
        viewCategory=findViewById(R.id.viewCategory);

        rgUserInfo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioBtnStore){
                    userStatus=0;
                    Toast.makeText(RegisterActivity.this, "store 사용자입니다.", Toast.LENGTH_SHORT).show();
                    mEtStoreAddress.setVisibility(View.VISIBLE);
                    viewAddress.setVisibility(View.VISIBLE);
                    mEtStoreCategory.setVisibility(View.VISIBLE);
                    viewCategory.setVisibility(View.VISIBLE);
                } else if (i == R.id.radioBtnCustomer){
                    userStatus=1;
                    Toast.makeText(RegisterActivity.this, "customer 사용자입니다.", Toast.LENGTH_SHORT).show();
                    mEtStoreAddress.setVisibility(View.INVISIBLE);
                    viewAddress.setVisibility(View.INVISIBLE);
                    mEtStoreCategory.setVisibility(View.INVISIBLE);
                    viewCategory.setVisibility(View.INVISIBLE);
                }

            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPwd=mEtPwd.getText().toString();
                String strEmail=mEtEmail.getText().toString();

                String strNickname=mEtNickname.getText().toString();
                String strAddress=mEtStoreAddress.getText().toString();
                String strStoreCategory=mEtStoreCategory.getText().toString();

                if (userStatus==2) {
                    Toast.makeText(RegisterActivity.this, "사용자 정보를 확인해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // Firebase Auth 진행
                    mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                                UserAccount account=new UserAccount();
                                account.setIdToken(firebaseUser.getUid());  // 로그인하면 부여되는 고유값
                                account.setEmailId(firebaseUser.getEmail());
                                account.setPassword(strPwd);

                                account.setNickname(strNickname);
                                if (userStatus==0) {
                                    account.setAddress(strAddress);
                                    account.setCategory(strStoreCategory);
                                    account.setGetWhose("store");
                                    mDatabaseRef.child("UserAccount/Store").child(firebaseUser.getUid()).setValue(account);
                                } else if (userStatus==1) {
                                    account.setGetWhose("customer");
                                    mDatabaseRef.child("UserAccount/Customer").child(firebaseUser.getUid()).setValue(account);
                                }
                                Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        //image 클릭시
        imageViewProfile=(ImageView) findViewById(R.id.iv_register_profile);
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi = data.getData();
            imageViewProfile.setImageURI(selectedImageURi);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef;

        if (userStatus==0) {
            riversRef=storageReference.child("images/profile/store/"+randomKey);
            riversRef.putFile(selectedImageURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed to Upload.", Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progressPercent=(100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    pd.setMessage("Percentage: "+(int)progressPercent+" %");
                }
            });
        } else if (userStatus==1) {
            riversRef=storageReference.child("images/profile/customer/"+randomKey);
            riversRef.putFile(selectedImageURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed to Upload.", Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progressPercent=(100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    pd.setMessage("Percentage: "+(int)progressPercent+" %");
                }
            });
        }

    }

}
