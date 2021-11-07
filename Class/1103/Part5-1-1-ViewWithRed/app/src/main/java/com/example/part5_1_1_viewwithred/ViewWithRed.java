package com.example.part5_1_1_viewwithred;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ViewWithRed extends View {
    public ViewWithRed(Context context){
        super(context);
    }
    public ViewWithRed(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public ViewWithRed(Context context, AttributeSet attrs, int defaultStyle){
        super(context,attrs,defaultStyle);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        Paint circlePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.RED);
        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,canvas.getWidth()/3,circlePaint);
    }
}
