package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ImageView 객체 생성: resource 저장한 image에 대한 객체 생성
        ImageView iv_pinwheel=(ImageView)findViewById(R.id.pinwheel);

        //pinwheel 이미지를 360도 회전하기 위한 Animator class를 사용함
        //ObjectAnimator object=ObjectAnimator.ofFloat(iv_pinwheel,"rotation",360); //360도 회전
        ObjectAnimator object=ObjectAnimator.ofFloat(iv_pinwheel,"rotation",360);
        object.setInterpolator(new LinearInterpolator()); //일정한 속도로 회전
        object.setDuration(2000); //animation interval time 2 sec
        object.setRepeatCount(ValueAnimator.INFINITE); //loop
        object.start(); //animation start
    }
}