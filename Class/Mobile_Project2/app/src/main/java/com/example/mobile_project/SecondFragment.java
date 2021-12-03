package com.example.mobile_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mobile_project.databinding.FragmentSecondBinding;

//나의 식단관리와 약관리 페이지
public class SecondFragment extends Fragment {

        Button manage_food;
        Button manage_pill;
        MenuInflater inflater;

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
        ) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_second, container, false);
        }

        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            manage_food=view.findViewById(R.id.manage_food); //나의 식단관리 버튼
            manage_pill=view.findViewById(R.id.manage_pill); //나의 약 관리 버튼

            //나의 식단관리 버튼을 눌렀을 때
            manage_food.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FoodFragment);
                }
            });

            manage_pill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_PillFragment);
                }
            });


        }

        public boolean onCreateOptionsMenu(Menu menu) {
            //action bar에 메뉴 아이템 추가
            inflater=getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_main,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            int id=item.getItemId();

            //클릭한 목록 아이템 id별로 이동할 액티비티

            //운동
            if (id == R.id.menu1){
            /*
            Intent it = new Intent(this, exerciseclass명.class);
            startActivity(it);
            finish();
            return true;
            */
            }

            //식단
            if (id == R.id.menu2){
            }

            //나의 다이어리
            if (id == R.id.menu3){
            /*
            Intent it = new Intent(this, exerciseclass명.class);
            startActivity(it);
            finish();
            return true;
            */
            }

            //로그아웃
            if (id == R.id.menu4){
            /*
            Intent it = new Intent(this, exerciseclass명.class);
            startActivity(it);
            finish();
            return true;
            */
            }

            return super.onOptionsItemSelected(item);
        }

    }