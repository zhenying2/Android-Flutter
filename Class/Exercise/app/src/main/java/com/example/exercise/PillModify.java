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

public class PillModify extends AppCompatActivity {

    Button modify_btn;
    DBManager2 dbmanager;
    SQLiteDatabase sqlitedb;

    String str_name1,str_memo1;
    String re_name1,re_memo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_modify);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar 뒤로가기 기능
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //정보 출력 뷰 인식
        EditText ed_name1=(EditText)findViewById(R.id.name1);
        EditText ed_memo1=(EditText)findViewById(R.id.pillmemo1);

        modify_btn=(Button)findViewById(R.id.pillmemo_resave);

        //전달받은 날짜 추출
        Intent it=getIntent();
        str_name1=it.getStringExtra("it_name");

        //table에서 해당 date 추출
        try{
            dbmanager=new DBManager2(this);
            sqlitedb=dbmanager.getReadableDatabase();

            //data 추출
            Cursor cursor=sqlitedb.query("Pill",null,"name = ?",new String[]{str_name1},null,null,null,null);

            //추출된 데이터 할당
            if (cursor.moveToNext()){
                str_name1=cursor.getString(cursor.getColumnIndexOrThrow("name"));
                str_memo1=cursor.getString(cursor.getColumnIndexOrThrow("memo"));
            }

            sqlitedb.close();
            dbmanager.close();
        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        //추출한 데이터 출력
        //약명
        ed_name1.setText(str_name1);

        //약메모
        ed_memo1.setText(str_memo1);

        //수정 버튼 클릭했을때,
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //출력값도 갱신
                re_name1=ed_name1.getText().toString();
                re_memo1=ed_memo1.getText().toString();
                update(re_name1,re_memo1);
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

    //수정 버튼 클릭시
    void update(String re_name1,String re_memo1){
        try{
            sqlitedb=dbmanager.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("name",re_name1);
            contentValues.put("memo",re_memo1);
            sqlitedb.update("Pill",contentValues,"name = ?",new String[]{re_name1});
            sqlitedb.close();
            dbmanager.close();

            //정보 출력 뷰 인식
            EditText ed_name1=(EditText)findViewById(R.id.name1);
            EditText ed_memo1=(EditText)findViewById(R.id.pillmemo1);

            //데이터 출력 변경
            //약명
            ed_name1.setText(re_name1);

            //약메모
            ed_memo1.setText(re_memo1);

        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this,"수정완료",Toast.LENGTH_SHORT).show();
        Intent it=new Intent(this,ManagePill.class);
        startActivity(it);
    }

    //삭제 버튼 클릭시
    public void delete(View v){
        try{
            dbmanager=new DBManager2(this);
            sqlitedb=dbmanager.getReadableDatabase();
            sqlitedb.delete("Pill","name = ?",new String[]{str_name1});
            sqlitedb.close();
            dbmanager.close();
        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this,"삭제되었습니다.",Toast.LENGTH_SHORT).show();
        Intent it=new Intent(this,ManagePill.class);
        startActivity(it);
    }
}