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

public class ManagePill extends AppCompatActivity implements View.OnClickListener {

    Button write_btn;
    DBManager2 dbmanager;
    SQLiteDatabase sqlitedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pill);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //pill memo 레이아웃 인식
        LinearLayout layout=(LinearLayout)findViewById(R.id.pillmemolist);

        try{
            //Pill table에서 정보 추출
            dbmanager=new DBManager2(this);
            sqlitedb=dbmanager.getReadableDatabase();
            Cursor cursor=sqlitedb.query("Pill",null,null,null,null,null,null);

            //각 정보 반복 출력을 통한 목록화
            int i=0;
            while(cursor.moveToNext()){
                //정보 추출
                String str_name=cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String str_memo=cursor.getString(cursor.getColumnIndexOrThrow("memo"));

                //정보 목록 아이템 만들기
                LinearLayout layout_item=new LinearLayout(this);
                layout_item.setOrientation(LinearLayout.VERTICAL);
                layout_item.setPadding(5,5,5,5);
                layout_item.setId(i);
                layout_item.setTag(str_name);

                //약이름
                TextView tv_name=new TextView(this);
                tv_name.setText(str_name);
                layout_item.addView(tv_name);

                //클릭 리스너 설정
                layout_item.setOnClickListener(this);

                //6 인물정보 레이아웃에 추가
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
        write_btn=findViewById(R.id.write_pill_btn);

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getApplicationContext(),PillWrite.class);
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
        String str_name=(String)layout_item.getTag();

        //글 수정 액티비티 호출
        Intent it=new Intent(this,PillModify.class);
        it.putExtra("it_name",str_name);
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