package com.example.part5_2_1_bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class MyBitMap extends View {
    public MyBitMap(Context context){
        super(context);
    }
    public MyBitMap(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public MyBitMap(Context context, AttributeSet attrs, int defaultStyle){
        super(context,attrs,defaultStyle);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);

        Bitmap mypic= BitmapFactory.decodeResource(getResources(),R.drawable.mydog);

        //draw the big middle
        Bitmap mypicMedium=Bitmap.createScaledBitmap(mypic,450,400,false);
        canvas.drawBitmap(mypicMedium,330,600,null);

        //create the thumbnail
        Bitmap mypicSmall=Bitmap.createScaledBitmap(mypic,250,300,false);

        Matrix maxTopLeft=new Matrix();
        maxTopLeft.preRotate(30);

        Matrix maxBottomLeft=new Matrix();
        maxBottomLeft.preRotate(-30);

        Matrix maxTopRight=new Matrix();
        maxTopRight.preRotate(-30);
        maxTopRight.preScale(-1,1); //mirror image

        Matrix maxBottomRight=new Matrix();
        maxBottomRight.preRotate(30);
        maxBottomRight.preScale(-1,1);

        Bitmap mypicTopLeft=Bitmap.createBitmap(mypicSmall,0,0,mypicSmall.getWidth(),mypicSmall.getHeight(),maxTopLeft,false);
        Bitmap mypicBottomLeft=Bitmap.createBitmap(mypicSmall,0,0,mypicSmall.getWidth(),mypicSmall.getHeight(),maxBottomLeft,false);
        Bitmap mypicTopRight=Bitmap.createBitmap(mypicSmall,0,0,mypicSmall.getWidth(),mypicSmall.getHeight(),maxTopRight,false);
        Bitmap mypicBottomRight=Bitmap.createBitmap(mypicSmall,0,0,mypicSmall.getWidth(),mypicSmall.getHeight(),maxBottomRight,false);

        mypicSmall.recycle();
        mypic.recycle();

        canvas.drawBitmap(mypicTopLeft,110,370,null);
        canvas.drawBitmap(mypicBottomLeft,110,800,null);
        canvas.drawBitmap(mypicTopRight,580,370,null);
        canvas.drawBitmap(mypicBottomRight,580,800,null);
    }
}
