package com.example.nolfi.writepage;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nolfi.MainActivity;
import com.example.nolfi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class WritePageFragment3 extends Fragment {
    //메인 액티비티 객체 선언
    MainActivity activity;
    ImageView img_donate;
    Uri selectedImageURi;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private EditText mEtDonateProductName, mEtDonateProductCategory, mEtDonatePurchaseDate, mEtDonateExpirationDate;
    private EditText mEtDonateProductLocation, mEtDonateProductContact, mEtDonateProductDescription;

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //현재 소속된 액티비티를 메인 액티비티로 한다.
        activity = (MainActivity) getActivity();
    }
    @Override public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_writepage3,container,false);
        ImageView imageView1=v.findViewById(R.id.down_arrow3);
        registerForContextMenu(imageView1);

        mFirebaseAuth = mFirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("NolFI");
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        //imageview eventlistener 연결
        // img_donate=v.findViewById(R.id.donate_product_photo);
        // img_donate.setOnClickListener(this);

        mEtDonateProductName=v.findViewById(R.id.donate_product_name);
        mEtDonateProductCategory=v.findViewById(R.id.donate_category);
        mEtDonatePurchaseDate=v.findViewById(R.id.donate_purchase_date);
        mEtDonateExpirationDate=v.findViewById(R.id.donate_expiration_date);

        mEtDonateProductLocation=v.findViewById(R.id.donate_location);
        mEtDonateProductContact=v.findViewById(R.id.donate_contact);
        mEtDonateProductDescription=v.findViewById(R.id.donate_description);

        // enroll 버튼
        Button btn_sell_enroll=(Button)v.findViewById(R.id.donate_enroll_button);
        btn_sell_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                final String randomKey= UUID.randomUUID().toString();

                String strProductName=mEtDonateProductName.getText().toString();
                String strCategory=mEtDonateProductCategory.getText().toString();

                String strPurchaseDate=mEtDonatePurchaseDate.getText().toString();
                String strExpirationDate=mEtDonateExpirationDate.getText().toString();
                String strLocation=mEtDonateProductLocation.getText().toString();
                String strContact=mEtDonateProductContact.getText().toString();
                String strDescription=mEtDonateProductDescription.getText().toString();

                HashMap donateData = new HashMap<>();
                donateData.put("product name", strProductName);
                donateData.put("category", strCategory);
                donateData.put("purchase date", strPurchaseDate);
                donateData.put("expiration date", strExpirationDate);

                donateData.put("location", strLocation);
                donateData.put("contact", strContact);
                donateData.put("detailed description", strDescription);


                // 삭제는 removeValue()
                mDatabaseRef.child("UserAccount/Store").child(firebaseUser.getUid()).child("donate/").child(randomKey).updateChildren(donateData);
            }
        });

        //image 클릭시
        img_donate=(ImageView) v.findViewById(R.id.donate_product_photo);
        img_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/");
                startActivityForResult(intent,1);
            }
        });

        return v;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.write_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            //sell 클릭
            case R.id.write_menu1:
                activity.onFragmentChange(1);
                Toast.makeText(getContext(), "Sell click", Toast.LENGTH_LONG).show();
                break;

            //group purchase 클릭
            case R.id.write_menu2:
                activity.onFragmentChange(2);
                Toast.makeText(getContext(), "Group Purchase click", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //imageview에 image 보여주기
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode ==  RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi = data.getData();
            img_donate.setImageURI(selectedImageURi);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef;

        riversRef=storageReference.child("images/product/donate/"+randomKey);
        riversRef.putFile(selectedImageURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to Upload.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /*
    @Override
    public void onClick(View view) {
            //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/");
            startActivityForResult(intent, 1);
    }
    */
}
