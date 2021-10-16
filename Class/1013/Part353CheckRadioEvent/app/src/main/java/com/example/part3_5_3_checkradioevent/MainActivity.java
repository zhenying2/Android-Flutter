 package com.example.part3_5_3_checkradioevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {
    TextView objTV;
    LinearLayout objLayout;
    RadioButton objRBSet, objRBReset;
    CheckBox objRed,objGreen,objBlue;

    int backcolor=0xFF000000;
    String strData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objTV=(TextView)findViewById(R.id.textView3);
        objLayout=(LinearLayout)findViewById(R.id.MainLayout);
        objRBSet=(RadioButton)findViewById(R.id.radioButton1);
        objRBReset=(RadioButton)findViewById(R.id.radioButton2);
        objRBSet.setOnClickListener(rdbButtonListener);
        objRBReset.setOnClickListener(rdbButtonListener);

        objRed=(CheckBox)findViewById(R.id.checkBox);
        objGreen=(CheckBox)findViewById(R.id.checkBox2);
        objBlue=(CheckBox)findViewById(R.id.checkBox3);
        objRed.setOnClickListener(setColorListener);
        objGreen.setOnClickListener(setColorListener);
        objBlue.setOnClickListener(setColorListener);
    }

     View.OnClickListener rdbButtonListener=new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if (objRBSet.isChecked()){
                 strData="Color Setting Mode : "+objRBSet.getText().toString();
             }else{
                 strData="Color Setting Mode :"+objRBReset.getText().toString();
                 objRed.setChecked(false);
                 objGreen.setChecked(false);
                 objBlue.setChecked(false);
                 objTV.setBackgroundColor(0xFFFFFFFF);
                 objTV.setTextColor(0xFF000000);
             }
             objTV.setText(strData);
         }
     };

    View.OnClickListener setColorListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CheckBox chkColor=(CheckBox)view;
            if (objRBSet.isChecked()){
                strData="Color Setting Mode : "+objRBSet.getText().toString();
                if(objRed.isChecked())backcolor|=0xFFFF0000;
                if(objGreen.isChecked())backcolor|=0xFF00FF00;
                if(objBlue.isChecked())backcolor|=0xFF0000FF;
                objTV.setBackgroundColor(backcolor);
                objTV.setTextColor(0xFF000000);
                objLayout.setBackgroundColor(backcolor);
                objTV.setBackgroundColor(backcolor);
                backcolor=0xFF000000;
            }else{
                objTV.setTextColor(0xFF000000);
                objTV.setText("Color Setting Mode를 Set으로 지정하세요.");
                chkColor.setChecked(false);
            }
        }
    };
}