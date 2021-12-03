package com.example.mobile_project;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FoodWriteFragment extends Fragment{

    Button save_btn;
    DBManager dbmanager;
    SQLiteDatabase sqlitedb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_write, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    //나의 저장 버튼을 눌렀을 때
        save_btn=view.findViewById(R.id.foodmemo_save);
        save_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            register(view);
            NavHostFragment.findNavController(FoodWriteFragment.this)
                    .navigate(R.id.action_FoodWriteFragment_to_FoodFragment);
        }
    });
    }

    //저장 버튼 클릭시
    public void register(View v){
        //입력한 정보 추출

        //날짜
        EditText et_date=(EditText)getView().findViewById(R.id.date);
        String str_date=et_date.getText().toString();

        //아침
        EditText et_breakfast=(EditText)getView().findViewById(R.id.breakfast);
        String str_breakfast=et_breakfast.getText().toString();

        //점심
        EditText et_lunch=(EditText)getView().findViewById(R.id.lunch);
        String str_lunch=et_lunch.getText().toString();

        //저녁
        EditText et_dinner=(EditText)getView().findViewById(R.id.dinner);
        String str_dinner=et_dinner.getText().toString();

        //2. 테이블에 정보 추가
        try {
            dbmanager = new DBManager(getContext());
            sqlitedb = dbmanager.getWritableDatabase();

            //테이블에 추가할 데이터 할당
            ContentValues values = new ContentValues();
            values.put("date", str_date);
            values.put("breakfast", str_breakfast);
            values.put("lunch", str_lunch);
            values.put("dinner", str_dinner);

            //테이블에 추가
            long newRowId = sqlitedb.insert("Food", null, values);

            sqlitedb.close();
            dbmanager.close();
        }catch(SQLiteException e){

        }
    }
}
