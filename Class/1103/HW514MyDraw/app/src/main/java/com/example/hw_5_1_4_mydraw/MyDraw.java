package com.example.hw_5_1_4_mydraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyDraw extends View {
    public MyDraw(Context context){
        super(context);
    }
    public MyDraw(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public MyDraw(Context context, AttributeSet attrs, int defaultStyle){
        super(context,attrs,defaultStyle);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        //안드로이드
        Paint android=new Paint();
        android.setColor(Color.GREEN);

        //안드로이드 얼굴
        RectF face = new RectF();
        face.set(280, 400, 780, 900);
        canvas.drawArc(face,0,-180,true,android);
        canvas.save(); //회전 전 상태 저장

        //안드로이드 왼쪽 뿔
        canvas.rotate(-45);//뿔 회전을 위함
        RectF left_horn=new RectF(-100,500,-50,800);
        canvas.drawRoundRect(left_horn,30,30,android);
        canvas.restore(); //원래 캔버스로 재개
        canvas.save(); //회전 전 상태 저장

        //안드로이드 오른쪽 뿔
        canvas.rotate(45);//뿔 회전을 위함
        RectF right_horn=new RectF(800,-250,850,50);
        canvas.drawRoundRect(right_horn,30,30,android);
        canvas.restore(); //원래 캔버스로 재개

        //안드로이드 몸
        RectF body=new RectF(280,680,780,1020);
        canvas.drawRoundRect(body,20,20,android);

        //안드로이드 왼쪽 다리
        RectF left_leg=new RectF(380,880,500,1170);
        canvas.drawRoundRect(left_leg,30,30,android);

        //안드로이드 오른쪽 다리
        RectF right_leg=new RectF(570,880,690,1170);
        canvas.drawRoundRect(right_leg,30,30,android);

        //안드로이드 왼쪽 팔
        RectF left_arm=new RectF(150,680,250,880);
        canvas.drawRoundRect(left_arm,30,30,android);

        //안드로이드 오른쪽 팔
        RectF right_arm=new RectF(810,680,910,880);
        canvas.drawRoundRect(right_arm,30,30,android);

        //안드로이드 눈
        Paint eye=new Paint();
        eye.setColor(Color.WHITE);
        //안드로이드 왼쪽 눈
        canvas.drawCircle(430,550,20,eye);
        //안드로이드 오른쪽 눈
        canvas.drawCircle(630,550,20,eye);


    }
}
