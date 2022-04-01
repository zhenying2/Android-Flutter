package com.example.nolfi.mypage;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.nolfi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MyPageFragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mypage1,container,false);

        Button bookButton = (Button)v.findViewById(R.id.btn_bookmark);
        Button productButton = (Button)v.findViewById(R.id.btn_productlist);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyPage_Bookmark.class);
                startActivity(intent);
            }
        });

        DatabaseReference dataRef;
        dataRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid= user.getUid();
        DatabaseReference whois = dataRef.child(uid).child("getWhose");
        String whos=whois.toString();
        DatabaseReference whos1=dataRef.child("NolFI").child("UserAccount").child("Customer").child(uid).child("getWhose");
        DatabaseReference whos2=dataRef.child("NolFI").child("UserAccount").child("Store").child(uid).child("getWhose");

        dataRef.child("NolFI").child("UserAccount").child("Customer").child(uid).child("getWhose").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("rrrr", "Error getting data", task.getException());
                }
                else {
                    Log.d("rrrr", String.valueOf(task.getResult().getValue()));
                    String check = String.valueOf(task.getResult().getValue());
                    Log.d("rrrr", check);

                    if(check.equals("customer")){
                        productButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), MyPage_Customer.class);

                                startActivity(intent);
                            }
                        });

                    }
                    else{
                        productButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), Mypage_Product.class);
                                startActivity(intent);
                            }
                        });
                    }

                }
            }
        });


        Log.d("rrrr","who"+whos);
        Log.d("rrrr","who1"+whos1);
        Log.d("rrrr","who2"+whos2);



        return v;
        }
}