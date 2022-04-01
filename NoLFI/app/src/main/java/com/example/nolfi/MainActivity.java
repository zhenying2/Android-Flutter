package com.example.nolfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.nolfi.mainpage.MainPageFragment1;
import com.example.nolfi.mainpage.MainPageFragment2;
import com.example.nolfi.mainpage.MainPageFragment3;
import com.example.nolfi.mypage.MyPageFragment1;
import com.example.nolfi.mypage.MyPage_Customer;
import com.example.nolfi.writepage.WritePageFragment1;
import com.example.nolfi.writepage.WritePageFragment2;
import com.example.nolfi.writepage.WritePageFragment3;
import com.example.nolfi.writepage.WritePageFragment_my;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView=findViewById(R.id.bottom_navigation);

        //첫 화면 띄우기
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container,new MainPageFragment1()).commit();

        //case 함수를 통해 클릭 받을 때마다 화면 변경하기 (bottom navi)
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                DatabaseReference dataRef;
                dataRef = FirebaseDatabase.getInstance().getReference();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid= user.getUid();
                DatabaseReference whois = dataRef.child(uid).child("getWhose");
                String whos=whois.toString();
                DatabaseReference whos1=dataRef.child("NolFI").child("UserAccount").child("Customer").child(uid).child("getWhose");
                DatabaseReference whos2=dataRef.child("NolFI").child("UserAccount").child("Store").child(uid).child("getWhose");

                switch (item.getItemId()){
                    //mainpage
                    case R.id.menu_1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new MainPageFragment1()).commit();
                        break;
                    //write page
                    case R.id.menu_2:

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
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new WritePageFragment_my()).commit();

                                    }
                                    else{
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new WritePageFragment1()).commit();
                                    }

                                }
                            }
                        });

                        Log.d("rrrr","who"+whos);
                        Log.d("rrrr","who1"+whos1);
                        Log.d("rrrr","who2"+whos2);

                        break;
                    //mypage
                    case R.id.menu_3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new MyPageFragment1()).commit();
                        break;

                }
                return true;
            }
        });
    }
    //프래그먼트가 바뀔때 작동하게끔 작성한 메서드
    public void onFragmentChange(int fragmentNum) {
        //sell
        if (fragmentNum == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WritePageFragment1()).commit();
        } else if (fragmentNum == 2) { //group purchase
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WritePageFragment2()).commit();
        } else if (fragmentNum == 3) { //donate
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WritePageFragment3()).commit();
        }else if (fragmentNum == 4) { //main page -> mainpage2로 이동
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new MainPageFragment2()).commit();
        }else if (fragmentNum == 5) { //main page2 -> mainpage3로 이동
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new MainPageFragment3()).commit();
        }
    }
}