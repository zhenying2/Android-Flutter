package com.example.exercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class FoodWrite extends AppCompatActivity {

    int GET_GALLERY_IMAGE1=1;
    int GET_GALLERY_IMAGE2=2;
    int GET_GALLERY_IMAGE3=3;

    ImageView img_breakfast,img_lunch,img_dinner;
    Uri selectedImageURi1,selectedImageURi2,selectedImageURi3;

    DBManager dbmanager;
    SQLiteDatabase sqlitedb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_write);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //toolbar 뒤로가기 기능
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //imageview_breakfast
        img_breakfast=(ImageView) findViewById(R.id.img_breakfast);
        img_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/");
                startActivityForResult(intent,GET_GALLERY_IMAGE1);
            }
        });

        //imageview_lunch
        img_lunch=(ImageView) findViewById(R.id.img_lunch);
        img_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/");
                startActivityForResult(intent,GET_GALLERY_IMAGE2);
            }
        });

        //imageview_dinner
        img_dinner=(ImageView) findViewById(R.id.img_dinner);
        img_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/");
                startActivityForResult(intent,GET_GALLERY_IMAGE3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE1 && resultCode == RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi1 = data.getData();
            img_breakfast.setImageURI(selectedImageURi1);
        }
        if (requestCode == GET_GALLERY_IMAGE2 && resultCode == RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi2 = data.getData();
            img_lunch.setImageURI(selectedImageURi2);
        }
        if (requestCode == GET_GALLERY_IMAGE3 && resultCode == RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi3 = data.getData();
            img_dinner.setImageURI(selectedImageURi3);
        }
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
            values.put("uri1",selectedImageURi1.toString());
            values.put("uri2",selectedImageURi2.toString());
            values.put("uri3",selectedImageURi3.toString());

            //table에 추가
            long newRowId = sqlitedb.insert("Food", null, values);

            sqlitedb.close();
            dbmanager.close();

            //ManageFood activity로 이동
            Intent it = new Intent(this,ManageFood.class);
            it.putExtra("it_name",str_date);
            Toast.makeText(this,"저장 성공!",Toast.LENGTH_SHORT).show();
            startActivity(it);
        }catch(SQLiteException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}