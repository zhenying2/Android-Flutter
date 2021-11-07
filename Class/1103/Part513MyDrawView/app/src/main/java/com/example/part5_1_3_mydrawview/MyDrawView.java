package com.example.part5_1_3_mydrawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static com.example.part5_1_3_mydrawview.MainActivity.curColor;
import static com.example.part5_1_3_mydrawview.MainActivity.curShape;

public class MyDrawView extends View {
    public MyDrawView(Context context){
        super(context);
    }
    public MyDrawView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public MyDrawView(Context context, AttributeSet attrs, int defaultStyle){
        super(context,attrs,defaultStyle);
    }

    int startX=-1, startY=-1, stopX=-1,stopY=-1;
    final static int LINE=1, CIRCLE=2, RECTANGLE=3;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=(int) event.getX();
                startY=(int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                stopX=(int) event.getX();
                stopY=(int) event.getY();
                this.invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(curColor); //추가

        switch (curShape){
            case LINE:
                canvas.drawLine(startX,startY,stopX,stopY,paint);
                break;
            case CIRCLE:
                int radius=(int) Math.sqrt(Math.pow(stopX-startX,2)+Math.pow(stopY-startY,2));
                canvas.drawCircle(startX,startY,radius,paint);
                break;
            case RECTANGLE:
                Rect rect=new Rect(startX,startY,stopX,stopY);
                canvas.drawRect(rect,paint);
                break;
            default:
                curColor= Color.DKGRAY;
                canvas.drawLine(startX,startY,stopX,stopY,paint);
                break;
        }
    }
}
