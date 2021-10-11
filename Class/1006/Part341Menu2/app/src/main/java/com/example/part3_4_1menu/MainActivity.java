package com.example.part3_4_1menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //1번째 삽입
    TextView objTxtView; //textview 객체 선언
    LinearLayout objMainView;

    //group no.
    public static final int ID_GROUP_TEXT_COLOR=1;
    public static final int ID_GROUP_TEXT_STYLE=2;
    public static final int ID_GROUP_TEXT_SIZE=3;
    public static final int ID_GROUP_mnuicon1=4;
    public static final int ID_GROUP_mnuicon2=5;

    //text color item no.
    public static final int ID_COLOR_RED=11;
    public static final int ID_COLOR_GREEN=12;
    public static final int ID_COLOR_BLUE=13;

    //text style item no.
    public static final int ID_TEXT_NORMAL=21;
    public static final int ID_TEXT_BOLD=22;
    public static final int ID_TEXT_ITALIC=23;

    //text size item no.
    public static final int ID_TEXTSIZE_10P=31;
    public static final int ID_TEXTSIZE_18P=32;
    public static final int ID_TEXTSIZE_24P=33;

    //background color item no.
    public static final int COLOR_RED=41;
    public static final int COLOR_GREEN=42;
    public static final int COLOR_BLUE=43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2번째 삽입
        objTxtView = (TextView) findViewById(R.id.textView1);
        objMainView = (LinearLayout) findViewById(R.id.MainLayout);
        registerForContextMenu(objTxtView); //context menu 제공
    }

    //3번째 3개의 method 추가
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        //MenuItem mnuTextColor=menu.add("Text Color");
        //MenuItem mnuTextStyle=menu.add("Text Style");
        //MenuItem mnuTextSize=menu.add("Text Size");

        //2nd for submenu
        SubMenu mnuTextColor=menu.addSubMenu("Text Color");
        mnuTextColor.add(ID_GROUP_TEXT_COLOR,ID_COLOR_RED,1,"Red");
        mnuTextColor.add(ID_GROUP_TEXT_COLOR,ID_COLOR_GREEN,2,"Green");
        mnuTextColor.add(ID_GROUP_TEXT_COLOR,ID_COLOR_BLUE,3,"Blue");

        SubMenu mnuTextStyle=menu.addSubMenu("Text Style");
        mnuTextStyle.add(ID_GROUP_TEXT_STYLE,ID_TEXT_NORMAL,1,"Normal").setChecked(true);
        mnuTextStyle.add(ID_GROUP_TEXT_STYLE,ID_TEXT_BOLD,2,"Bold");
        mnuTextStyle.add(ID_GROUP_TEXT_STYLE,ID_TEXT_ITALIC,3,"Italic");
        mnuTextStyle.setGroupCheckable(ID_GROUP_TEXT_SIZE,true,true);

        SubMenu mnuTextSize=menu.addSubMenu("Text Size");
        mnuTextSize.add(ID_GROUP_TEXT_SIZE,ID_TEXTSIZE_10P,1,"10pt");
        mnuTextSize.add(ID_GROUP_TEXT_SIZE,ID_TEXTSIZE_18P,2,"18pt");
        mnuTextSize.add(ID_GROUP_TEXT_SIZE,ID_TEXTSIZE_24P,3,"24pt");

        SubMenu etc=menu.addSubMenu("Etc");
        SubMenu mnuicon1=etc.addSubMenu("mnuicon1");
        mnuicon1.setIcon(R.drawable.beaver);

        SubMenu mnuicon2=etc.addSubMenu("mnuicon2");
        mnuicon2.setIcon(R.drawable.panda);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case ID_COLOR_RED:
                objTxtView.setTextColor(Color.RED);
                return true;
            case ID_COLOR_GREEN:
                objTxtView.setTextColor(Color.GREEN);
                return true;
            case ID_COLOR_BLUE:
                objTxtView.setTextColor(Color.BLUE);
                return true;
            case ID_TEXT_NORMAL:
                objTxtView.setTypeface(Typeface.DEFAULT,Typeface.NORMAL);
                item.setChecked(true);
                return true;
            case ID_TEXT_BOLD:
                objTxtView.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
                item.setChecked(true);
                return true;
            case ID_TEXT_ITALIC:
                objTxtView.setTypeface(Typeface.DEFAULT,Typeface.ITALIC);
                item.setChecked(true);
                return true;
            case ID_TEXTSIZE_10P:
                objTxtView.setTextSize(10);
                item.setChecked(true);
            case ID_TEXTSIZE_18P:
                objTxtView.setTextSize(18);
                item.setChecked(true);
            case ID_TEXTSIZE_24P:
                objTxtView.setTextSize(24);
                item.setChecked(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        SubMenu mnuBackGroundColor=menu.addSubMenu("Background");
        mnuBackGroundColor.add(Menu.NONE,COLOR_RED,Menu.NONE,"Red");
        mnuBackGroundColor.add(Menu.NONE,COLOR_GREEN,Menu.NONE,"Green");
        mnuBackGroundColor.add(Menu.NONE,COLOR_BLUE,Menu.NONE,"Blue");

        SubMenu mnuTextBackGroundColor=menu.addSubMenu("Text Background");
        mnuTextBackGroundColor.add(Menu.NONE,ID_COLOR_RED,Menu.NONE,"Red");
        mnuTextBackGroundColor.add(Menu.NONE,ID_COLOR_GREEN,Menu.NONE,"Green");
        mnuTextBackGroundColor.add(Menu.NONE,ID_COLOR_BLUE,Menu.NONE,"Blue");
        super.onCreateContextMenu(menu,v,menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case ID_COLOR_RED:
                objTxtView.setBackgroundColor(Color.RED);
               // objMainView.setBackgroundColor(Color.RED);
                return true;
            case ID_COLOR_BLUE:
                objTxtView.setBackgroundColor(Color.BLUE);
                //objMainView.setBackgroundColor(Color.BLUE);
                return true;
            case ID_COLOR_GREEN:
                objTxtView.setBackgroundColor(Color.GREEN);
                //objMainView.setBackgroundColor(Color.GREEN);
                return true;
            case COLOR_RED:
                objMainView.setBackgroundColor(Color.RED);
                return true;
            case COLOR_BLUE:
                objMainView.setBackgroundColor(Color.BLUE);
                return true;
            case COLOR_GREEN:
                objMainView.setBackgroundColor(Color.GREEN);
                return true;
        }
        return super.onContextItemSelected(item);
    }

}