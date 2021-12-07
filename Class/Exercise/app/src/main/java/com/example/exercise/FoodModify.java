package com.example.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FoodModify extends AppCompatActivity {

    Button modify_btn;
    DBManager dbmanager;
    SQLiteDatabase sqlitedb;

    String str_date1,str_breakfast1,str_lunch1,str_dinner1;
    String str_uri1,str_uri2,str_uri3;
    String re_date1,re_breakfast1,re_lunch1,re_dinner1;
    ImageView img_breakfast,img_lunch,img_dinner;

    //이미지 접근 권한
    String[] permissions={
        Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_modify);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar 뒤로가기 기능
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //정보 출력 뷰 인식
        EditText ed_date1=(EditText)findViewById(R.id.date1);
        EditText ed_breakfast1=(EditText)findViewById(R.id.breakfast1);
        EditText ed_lunch1=(EditText)findViewById(R.id.lunch1);
        EditText ed_dinner1=(EditText)findViewById(R.id.dinner1);

        modify_btn=(Button)findViewById(R.id.foodmemo_resave);

        //이미지 권한 요청
        ActivityCompat.requestPermissions(FoodModify.this,permissions,1);

        //이미지 정보 출력 뷰 인식
        img_breakfast=(ImageView) findViewById(R.id.img_breakfast_mod);
        img_lunch=(ImageView) findViewById(R.id.img_lunch_mod);
        img_dinner=(ImageView) findViewById(R.id.img_dinner_mod);


        //전달받은 날짜 추출
        Intent it=getIntent();
        str_date1=it.getStringExtra("it_name");

        //table에서 해당 date 추출
        try{
            dbmanager=new DBManager(this);
            sqlitedb=dbmanager.getReadableDatabase();

            //data 추출
            Cursor cursor=sqlitedb.query("Food",null,"date = ?",new String[]{str_date1},null,null,null,null);

            //추출된 데이터 할당
            if (cursor.moveToNext()){
                str_date1=cursor.getString(cursor.getColumnIndexOrThrow("date"));
                str_breakfast1=cursor.getString(cursor.getColumnIndexOrThrow("breakfast"));
                str_lunch1=cursor.getString(cursor.getColumnIndexOrThrow("lunch"));
                str_dinner1=cursor.getString(cursor.getColumnIndexOrThrow("dinner"));
                str_uri1=cursor.getString(cursor.getColumnIndexOrThrow("uri1"));
                str_uri2=cursor.getString(cursor.getColumnIndexOrThrow("uri2"));
                str_uri3=cursor.getString(cursor.getColumnIndexOrThrow("uri3"));
            }

            sqlitedb.close();
            dbmanager.close();
        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        //추출한 데이터 출력
        //날짜
        ed_date1.setText(str_date1);

        //아침
        ed_breakfast1.setText(str_breakfast1);

        //점심
        ed_lunch1.setText(str_lunch1);

        //저녁
        ed_dinner1.setText(str_dinner1);

        //아침사진
        try {
            Bitmap bitmap1= MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(str_uri1));
            img_breakfast.setImageBitmap(bitmap1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //점심사진
        try {
            Bitmap bitmap2= MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(str_uri2));
            img_lunch.setImageBitmap(bitmap2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //저녁사진
        try {
            Bitmap bitmap3= MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(str_uri3));
            img_dinner.setImageBitmap(bitmap3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //수정 버튼 클릭했을때,
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //출력값도 갱신
                re_date1=ed_date1.getText().toString();
                re_breakfast1=ed_breakfast1.getText().toString();
                re_lunch1=ed_lunch1.getText().toString();
                re_dinner1=ed_dinner1.getText().toString();
                update(re_date1,re_breakfast1,re_lunch1,re_dinner1);
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
    void update(String re_date1,String re_breakfast1,String re_lunch1,String re_dinner1){
        try{
        sqlitedb=dbmanager.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("date",re_date1);
        contentValues.put("breakfast",re_breakfast1);
        contentValues.put("lunch",re_lunch1);
        contentValues.put("dinner",re_dinner1);
        sqlitedb.update("Food",contentValues,"date = ?",new String[]{re_date1});
        sqlitedb.close();
        dbmanager.close();

        //정보 출력 뷰 인식
        EditText ed_date1=(EditText)findViewById(R.id.date1);
        EditText ed_breakfast1=(EditText)findViewById(R.id.breakfast1);
        EditText ed_lunch1=(EditText)findViewById(R.id.lunch1);
        EditText ed_dinner1=(EditText)findViewById(R.id.dinner1);


        //데이터 출력 변경
        //날짜
        ed_date1.setText(re_date1);

        //아침
        ed_breakfast1.setText(re_breakfast1);

        //점심
        ed_lunch1.setText(re_lunch1);

        //저녁
        ed_dinner1.setText(re_dinner1);

        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this,"수정완료",Toast.LENGTH_SHORT).show();
        Intent it=new Intent(this,ManageFood.class);
        startActivity(it);
    }

    //삭제 버튼 클릭시
    public void delete(View v){
        try{
        dbmanager=new DBManager(this);
        sqlitedb=dbmanager.getReadableDatabase();
        sqlitedb.delete("Food","date = ?",new String[]{str_date1});
        sqlitedb.close();
        dbmanager.close();
        }catch (SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this,"삭제되었습니다.",Toast.LENGTH_SHORT).show();
        Intent it=new Intent(this,ManageFood.class);
        startActivity(it);
    }
}