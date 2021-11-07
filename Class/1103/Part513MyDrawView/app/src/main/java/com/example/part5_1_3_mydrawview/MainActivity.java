package com.example.part5_1_3_mydrawview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public class MainActivity extends AppCompatActivity {

    static int LINE=1, CIRCLE=2, RECTANGLE=3;
    static int curShape=LINE;
    static int curColor= Color.DKGRAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new MyDrawView(this));
        setTitle("My Draw");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"선 그리기");
        menu.add(0,2,0,"원 그리기");
        menu.add(0,3,0,"사각형 그리기");

        SubMenu smenu=menu.addSubMenu("색상 변경 >>");
        smenu.add(0,4,0,"빨강");
        smenu.add(0,5,0,"초록");
        smenu.add(0,6,0,"파랑");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case 1:
                curShape=LINE; //선
                return true;
            case 2:
                curShape=CIRCLE; //원
                return true;
            case 3:
                curShape=RECTANGLE; //사각형
                return true;
            case 4:
                curColor=Color.RED;
                return true;
            case 5:
                curColor=Color.GREEN;
                return true;
            case 6:
                curColor=Color.BLUE;
                return true;
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        return super.onPrepareOptionsMenu(menu);
    }
}