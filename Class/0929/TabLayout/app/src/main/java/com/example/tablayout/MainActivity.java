package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Fragment fragment1,fragment2,fragment3,fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();
        fragment4=new Fragment4();

        getSupportFragmentManager().beginTransaction().add(R.id.frame,fragment1).commit();
        TabLayout tabs=(TabLayout)findViewById(R.id.tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                Fragment selected=null;
                switch (position){
                    case 0: selected=fragment1;break;
                    case 1: selected=fragment2;break;
                    case 2: selected=fragment3;break;
                    case 3: selected=fragment4;break;
                    default: ;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}