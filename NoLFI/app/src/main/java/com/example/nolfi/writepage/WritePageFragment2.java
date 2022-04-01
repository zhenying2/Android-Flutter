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

public class WritePageFragment2 extends Fragment {
    //메인 액티비티 객체 선언
    MainActivity activity;
    ImageView img_grouppurchase;
    Uri selectedImageURi;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private EditText mEtGroupProductName, mEtGroupProductCategory, mEtGroupProductNumber, mEtGroupDeadline;
    private EditText mEtGroupProductCost, mEtGroupProductLocation, mEtGroupProductContact, mEtGroupProductDescription;

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
        View v=inflater.inflate(R.layout.fragment_writepage2,container,false);
        ImageView imageView1=v.findViewById(R.id.down_arrow2);
        registerForContextMenu(imageView1);

        mFirebaseAuth = mFirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("NolFI");
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        //imageview eventlistener 연결
        // img_grouppurchase=v.findViewById(R.id.grouppurchase_product_photo);
        // img_grouppurchase.setOnClickListener(this);

        mEtGroupProductName=v.findViewById(R.id.group_purchase_product_name);
        mEtGroupProductCategory=v.findViewById(R.id.group_purchase_product_category);
        mEtGroupProductNumber=v.findViewById(R.id.group_purchase_number);
        mEtGroupDeadline=v.findViewById(R.id.group_purchase_deadline);

        mEtGroupProductCost=v.findViewById(R.id.group_purchase_product_cost);
        mEtGroupProductLocation=v.findViewById(R.id.group_purchase_product_location);
        mEtGroupProductContact=v.findViewById(R.id.group_purchase_product_contact);
        mEtGroupProductDescription=v.findViewById(R.id.group_purchase_detailed_description);

        // enroll 버튼
        Button btn_group_purchase_enroll=(Button)v.findViewById(R.id.group_purchase_enroll_button);
        btn_group_purchase_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                final String randomKey= UUID.randomUUID().toString();

                String strProductName=mEtGroupProductName.getText().toString();
                String strCategory=mEtGroupProductCategory.getText().toString();
                String strNumber=mEtGroupProductNumber.getText().toString();
                String strDeadline=mEtGroupDeadline.getText().toString();

                String strProductCost=mEtGroupProductCost.getText().toString();
                String strLocation=mEtGroupProductLocation.getText().toString();
                String strContact=mEtGroupProductContact.getText().toString();
                String strProductDescription=mEtGroupProductDescription.getText().toString();

                HashMap groupPurchaseData = new HashMap<>();
                groupPurchaseData.put("product name", strProductName);
                groupPurchaseData.put("category", strCategory);
                groupPurchaseData.put("product number", strNumber);
                groupPurchaseData.put("deadline", strDeadline);

                groupPurchaseData.put("product cost", strProductCost);
                groupPurchaseData.put("location", strLocation);
                groupPurchaseData.put("contact", strContact);
                groupPurchaseData.put("detailed description", strProductDescription);


                // 삭제는 removeValue()
                mDatabaseRef.child("UserAccount/Store").child(firebaseUser.getUid()).child("groupPurchase/").child(randomKey).updateChildren(groupPurchaseData);
            }
        });

        img_grouppurchase=(ImageView) v.findViewById(R.id.grouppurchase_product_photo);
        img_grouppurchase.setOnClickListener(new View.OnClickListener() {
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

            //donate 클릭
            case R.id.write_menu3:
                activity.onFragmentChange(3);
                Toast.makeText(getContext(), "Donate click", Toast.LENGTH_LONG).show();
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
            img_grouppurchase.setImageURI(selectedImageURi);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef;

        riversRef=storageReference.child("images/product/group_purchase/"+randomKey);
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