package com.example.part_5_3_2_chartgraphdraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    private final int Fragment_3 = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_piegraph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentView(Fragment_1);

            }
        });

        findViewById(R.id.btn_linegraph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_2);


            }
        });

        findViewById(R.id.btn_bargraph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(Fragment_3);


            }
        });

        FragmentView(Fragment_1);
    }

    private void FragmentView(int fragment){

        //FragmentTransactiom를 이용해 프래그먼트를 사용합니다.
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment){
            case 1:
                // 첫번 째 프래그먼트 호출
                Fragment1 fragment1 = new Fragment1();
                transaction.replace(R.id.main_frame, fragment1);
                transaction.commit();
                break;

            case 2:
                // 두번 째 프래그먼트 호출
                Fragment2 fragment2 = new Fragment2();
                transaction.replace(R.id.main_frame, fragment2);
                transaction.commit();
                break;

            case 3:
                // 세번 째 프래그먼트 호출
                Fragment3 fragment3 = new Fragment3();
                transaction.replace(R.id.main_frame, fragment3);
                transaction.commit();
                break;
        }

    }


}