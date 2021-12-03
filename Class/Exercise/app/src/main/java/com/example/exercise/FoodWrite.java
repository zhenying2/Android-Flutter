package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

public class FoodWrite extends AppCompatActivity {

    DBManager dbmanager;
    SQLiteDatabase sqlitedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_write);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //actionbar에 메뉴 아이템 추가
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //클릭한 목록 item id
        int id=item.getItemId();
        //클릭한 id별 이동 activity
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
            Intent it = new Intent(this, FoodMainActivity.class);
            startActivity(it);
            finish();
            return true;
        }

        //나의 다이어리
        if (id == R.id.menu3){
            Intent it = new Intent(this, MyDiary.class);
            startActivity(it);
            finish();
            return true;
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

    //저장 버튼 클릭시
    public void register(View v){
        //1. 입력한 정보 추출
        //날짜
        EditText et_date=(EditText)findViewById(R.id.date);
        String str_date=et_date.getText().toString();

        //아침
        EditText et_breakfast=(EditText)findViewById(R.id.breakfast);
        String str_breakfast=et_breakfast.getText().toString();

        //점심
        EditText et_lunch=(EditText)findViewById(R.id.lunch);
        String str_lunch=et_lunch.getText().toString();

        //저녁
        EditText et_dinner=(EditText)findViewById(R.id.dinner);
        String str_dinner=et_dinner.getText().toString();

        //2. table에 정보 추가
        try{
            dbmanager=new DBManager(this);
            sqlitedb=dbmanager.getWritableDatabase();

            //table에 추가할 데이터 할당
            ContentValues values = new ContentValues();
            values.put("date", str_date);
            values.put("breakfast", str_breakfast);
            values.put("lunch", str_lunch);
            values.put("dinner", str_dinner);

            //table에 추가
            long newRowId = sqlitedb.insert("Food", null, values);

            sqlitedb.close();
            dbmanager.close();

            //ManageFood activity로 이동
            Intent it = new Intent(this,ManageFood.class);
            it.putExtra("it_name",str_date);
            startActivity(it);
            finish();
        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}