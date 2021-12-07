package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class MyDiaryWrite extends AppCompatActivity {

    DBManager3 dbmanager;
    SQLiteDatabase sqlitedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary_write);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar 뒤로가기 기능
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            return true;
            */
        }

        //식단
        if (id == R.id.menu2){
            Intent it = new Intent(this, FoodMainActivity.class);
            startActivity(it);
            return true;
        }

        //나의 다이어리
        if (id == R.id.menu3){
            Intent it = new Intent(this, MyDiary.class);
            startActivity(it);
            return true;
        }

        //로그아웃
        if (id == R.id.menu4){
            /*
            Intent it = new Intent(this, exerciseclass명.class);
            startActivity(it);
            return true;
            */
        }

        //back키 눌렀을 때
        if (item.getItemId()==android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //저장 버튼 클릭시
    public void register(View v){
        //1. 입력한 정보 추출
        //날짜
        EditText et_date=(EditText)findViewById(R.id.diary_date);
        String str_date=et_date.getText().toString();

        //메모
        EditText et_memo=(EditText)findViewById(R.id.diary_memo);
        String str_memo=et_memo.getText().toString();

        //2. table에 정보 추가
        try{
            dbmanager=new DBManager3(this);
            sqlitedb=dbmanager.getWritableDatabase();

            //table에 추가할 데이터 할당
            ContentValues values = new ContentValues();
            values.put("date", str_date);
            values.put("memo", str_memo);

            //table에 추가
            long newRowId = sqlitedb.insert("Diary", null, values);

            sqlitedb.close();
            dbmanager.close();

            //MyDiary activity로 이동
            Intent it = new Intent(this,MyDiary.class);
            it.putExtra("it_name",str_date);
            startActivity(it);
        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}