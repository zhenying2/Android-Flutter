package com.example.mobile_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
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
import android.widget.LinearLayout;
import android.widget.TextView;

public class FoodFragment extends Fragment {

    Context ct;
    DBManager dbmanager;
    SQLiteDatabase sqlitedb;
    Button write_btn;
    LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ct = container.getContext();
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout=(LinearLayout)getView().findViewById(R.id.foodmemolist);

        try{
            //food 테이블에서 인물 정보 추출
            dbmanager=new DBManager(ct);
            sqlitedb=dbmanager.getReadableDatabase();
            Cursor cursor=sqlitedb.query("Food",null,null,null,null,null,null);
            //각 인물 정보의 반복 출력 통한 목록화
            int i=0;
            while(cursor.moveToNext()){
                //1. 정보 추출
                String date=cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String breakfast=cursor.getString(cursor.getColumnIndexOrThrow("breakfast"));
                String lunch=cursor.getString(cursor.getColumnIndexOrThrow("lunch"));
                String dinner=cursor.getString(cursor.getColumnIndexOrThrow("dinner"));

                //2. 인물정보 목록 아이템 만들기
                LinearLayout layout_item=new LinearLayout(ct);
                layout_item.setOrientation(LinearLayout.HORIZONTAL);
                layout_item.setId(i);
                layout_item.setTag(date);

                //3.1 날짜
                TextView tv_date=new TextView(ct);
                tv_date.setText(date);
                layout_item.addView(tv_date);

                //3.2 아침
                TextView tv_breakfast=new TextView(ct);
                tv_date.setText(breakfast);
                layout_item.addView(tv_breakfast);

                //3.3 점심
                TextView tv_lunch=new TextView(ct);
                tv_date.setText(lunch);
                layout_item.addView(tv_lunch);

                //3.4 저녁
                TextView tv_dinner=new TextView(ct);
                tv_date.setText(dinner);
                layout_item.addView(tv_dinner);

                //3.5 클릭 리스너 설정
                //layout_item.setOnClickListener(this);

                //3.6 인물정보 레이아웃에 추가
                layout.addView(layout_item);
                i++;
            }
            cursor.close();
            sqlitedb.close();
            dbmanager.close();
        }catch(SQLiteException e){

        }
        //글쓰기 버튼 클릭시
        write_btn=view.findViewById(R.id.write_food_btn);

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FoodFragment.this)
                        .navigate(R.id.action_FoodFragment_to_FoodWriteFragment);
            }

        });
    }
}