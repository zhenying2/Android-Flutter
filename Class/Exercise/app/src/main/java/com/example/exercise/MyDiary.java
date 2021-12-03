package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyDiary extends AppCompatActivity implements View.OnClickListener {

    Button write_btn;
    DBManager3 dbmanager;
    SQLiteDatabase sqlitedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //diary memo 레이아웃 인식
        LinearLayout layout=(LinearLayout)findViewById(R.id.diarymemolist);

        try{
            //diary table에서 정보 추출
            dbmanager=new DBManager3(this);
            sqlitedb=dbmanager.getReadableDatabase();
            Cursor cursor=sqlitedb.query("Diary",null,null,null,null,null,null);

            //각 정보 반복 출력을 통한 목록화
            int i=0;
            while(cursor.moveToNext()){
                //정보 추출
                String str_date=cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String str_memo=cursor.getString(cursor.getColumnIndexOrThrow("memo"));

                //정보 목록 아이템 만들기
                LinearLayout layout_item=new LinearLayout(this);
                layout_item.setOrientation(LinearLayout.HORIZONTAL);
                layout_item.setPadding(5,5,5,5);
                layout_item.setId(i);
                layout_item.setTag(str_date);

                //날짜
                TextView tv_date=new TextView(this);
                tv_date.setText(str_date);
                layout_item.addView(tv_date);

                //메모
                TextView tv_memo=new TextView(this);
                tv_memo.setText(str_memo);
                layout_item.addView(tv_memo);

                //클릭 리스너 설정
                layout_item.setOnClickListener(this);

                //레이아웃에 추가
                layout.addView(layout_item);
                i++;
            }
            cursor.close();
            sqlitedb.close();
            dbmanager.close();
        }catch(SQLException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        //글쓰기 버튼 클릭시
        write_btn=findViewById(R.id.write_diary_btn);

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),MyDiaryWrite.class);
                startActivity(it);
                finish();
            }

        });
    }

    @Override
    public void onClick(View view){
        int id=view.getId();

        //클릭한 목록 아이템 인식
        LinearLayout layout_item=(LinearLayout)findViewById(id);
        String str_date=(String)layout_item.getTag();

        //글 수정 액티비티 호출
        Intent it=new Intent(this,MyDiaryModify.class);
        it.putExtra("it_name",str_date);
        startActivity(it);
        finish();
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

}