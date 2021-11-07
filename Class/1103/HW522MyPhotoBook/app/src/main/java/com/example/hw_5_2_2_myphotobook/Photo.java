package com.example.hw_5_2_2_myphotobook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setTitle("나의 사진들");

        TextView tv_title=(TextView)findViewById(R.id.title);
        TextView tv_author=(TextView)findViewById(R.id.artist);
        TextView tv_time=(TextView)findViewById(R.id.time);
        ImageView iv_picture=(ImageView)findViewById(R.id.picture);

        Intent it=getIntent();
        String tag=it.getStringExtra("it_tag");

        Resources res=getResources();
        int id_title=res.getIdentifier("title"+tag,"string",getPackageName());
        String title=res.getString(id_title);
        tv_title.setText(title);

        int id_time=res.getIdentifier("time"+tag,"string",getPackageName());
        String time=res.getString(id_time);
        tv_time.setText(time);

        int id_author=res.getIdentifier("artist"+tag,"string",getPackageName());
        String author=res.getString(id_author);
        tv_author.setText(author);

        int id_picture=res.getIdentifier("picture"+tag,"string",getPackageName());
        String picture=res.getString(id_picture);
        int id_img=res.getIdentifier(picture,"drawable",getPackageName());

        Drawable drawable=res.getDrawable(id_img,null);
        //Drawable drawable=getResources().getDrawable(R.drawable.pic1,null)
        iv_picture.setBackground(drawable);
    }

    public void closePicture(View v){finish();}
}