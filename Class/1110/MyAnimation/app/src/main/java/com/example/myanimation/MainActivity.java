package com.example.myanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Activity 시작과 함께 배경음악을 재생한다. (배경음악_옹달샘) 1번만 실행되게 함
        //시간 순서 맞추다가 도저히 안될 것 같음
        startService(new Intent(getApplicationContext(),MusicService.class));

        //새벽 배경(토끼 등장)
        ImageView background_day=(ImageView)findViewById(R.id.background_day);
        Animation anim_daynight=AnimationUtils.loadAnimation(this,R.anim.daynight);
        background_day.startAnimation(anim_daynight);

        //밤 배경(노루 등장)
        ImageView background_night=(ImageView)findViewById(R.id.background_night);
        Animation anim_night=AnimationUtils.loadAnimation(this,R.anim.night);
        background_night.startAnimation(anim_night);

        //노루 등장
        ImageView deer=(ImageView)findViewById(R.id.deer);
        Animation anim_deer= AnimationUtils.loadAnimation(this,R.anim.deer);
        deer.startAnimation(anim_deer);

        //토끼 등장 (3마리 모양 변환 효과 줌)
        ImageView rabbit=(ImageView)findViewById(R.id.rabbit);
        Animation anim_rabbit= AnimationUtils.loadAnimation(this,R.anim.rabbit);
        rabbit.startAnimation(anim_rabbit);

        AnimationDrawable animation_drawable=new AnimationDrawable();
        BitmapDrawable frame1=(BitmapDrawable)getResources().getDrawable(R.drawable.rabbit1,null);
        BitmapDrawable frame2=(BitmapDrawable)getResources().getDrawable(R.drawable.rabbit2,null);
        BitmapDrawable frame3=(BitmapDrawable)getResources().getDrawable(R.drawable.rabbit3,null);

        animation_drawable.addFrame(frame1,1500);
        animation_drawable.addFrame(frame2,1500);
        animation_drawable.addFrame(frame3,1500);
        rabbit.setBackgroundDrawable(animation_drawable);
        animation_drawable.start();


    }
}