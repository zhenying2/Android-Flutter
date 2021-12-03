package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyDiaryModify extends AppCompatActivity {

    Button modify_btn;
    DBManager3 dbmanager;
    SQLiteDatabase sqlitedb;

    String str_date1,str_memo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary_modify);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //정보 출력 뷰 인식
        EditText ed_date1=(EditText)findViewById(R.id.diary_date1);
        EditText ed_memo1=(EditText)findViewById(R.id.diary_memo1);
        modify_btn=(Button)findViewById(R.id.diarymemo_resave);

        //전달받은 날짜 추출
        Intent it=getIntent();
        str_date1=it.getStringExtra("it_name");

        //table에서 해당 date 추출
        try{
            dbmanager=new DBManager3(this);
            sqlitedb=dbmanager.getReadableDatabase();

            //data 추출
            Cursor cursor=sqlitedb.query("Diary",null,"date = ?",new String[]{str_date1},null,null,null,null);

            //추출된 데이터 할당
            if (cursor.moveToNext()){
                str_date1=cursor.getString(cursor.getColumnIndexOrThrow("date"));
                str_memo1=cursor.getString(cursor.getColumnIndexOrThrow("memo"));
            }

            sqlitedb.close();
            dbmanager.close();
        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        //추출한 데이터 출력
        //날짜
        ed_date1.setText(str_date1);

        //메모
        ed_memo1.setText(str_memo1);

        //수정 버튼 클릭했을때,
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(ed_date1.toString(),ed_memo1.toString());
            }
        });
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

    //수정 버튼 클릭시
    void update(String ed_date1,String ed_memo1){
        try{
            sqlitedb=dbmanager.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("date",ed_date1);
            contentValues.put("memo",ed_memo1);

            //출력값도 갱신
            str_date1=ed_date1;
            str_memo1=ed_memo1;

            sqlitedb.update("Diary",contentValues,"date = ?",new String[]{str_date1});
            sqlitedb.close();
            dbmanager.close();
        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        Intent it=new Intent(this,MyDiary.class);
        startActivity(it);
        finish();
    }

    //삭제 버튼 클릭시
    public void delete(View v){
        try{
            dbmanager=new DBManager3(this);
            sqlitedb=dbmanager.getReadableDatabase();
            sqlitedb.delete("Diary","date = ?",new String[]{str_date1});
            sqlitedb.close();
            dbmanager.close();
        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        Intent it=new Intent(this,MyDiary.class);
        startActivity(it);
        finish();
    }
}