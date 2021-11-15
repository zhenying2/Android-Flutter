package com.example.part_5_4_3_movinganimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_moon=(ImageView)findViewById(R.id.moon);
        Animation anim_moon= AnimationUtils.loadAnimation(this,R.anim.moon);
        iv_moon.startAnimation(anim_moon);

        ImageView iv_bird=(ImageView)findViewById(R.id.bird);
        Animation anim_bird= AnimationUtils.loadAnimation(this,R.anim.bird);
        iv_bird.startAnimation(anim_bird);

        AnimationDrawable animation_drawable=new AnimationDrawable();
        BitmapDrawable frame1=(BitmapDrawable)getResources().getDrawable(R.drawable.bird1,null);
        BitmapDrawable frame2=(BitmapDrawable)getResources().getDrawable(R.drawable.bird2,null);
        BitmapDrawable frame3=(BitmapDrawable)getResources().getDrawable(R.drawable.bird3,null);
        BitmapDrawable frame4=(BitmapDrawable)getResources().getDrawable(R.drawable.bird4,null);

        animation_drawable.addFrame(frame1,200);
        animation_drawable.addFrame(frame2,200);
        animation_drawable.addFrame(frame3,200);
        animation_drawable.addFrame(frame4,200);
        iv_bird.setBackgroundDrawable(animation_drawable);
        animation_drawable.start();

        ImageView iv_mountain=(ImageView)findViewById(R.id.mountain);
        Animation anim_mountain=AnimationUtils.loadAnimation(this,R.anim.mountain);
        iv_mountain.startAnimation(anim_mountain);
    }
}