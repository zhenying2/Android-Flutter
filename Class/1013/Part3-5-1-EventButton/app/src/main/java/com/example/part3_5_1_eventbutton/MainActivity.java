package com.example.part3_5_1_eventbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView objTV;
    LinearLayout objMainLL, objSubLL;
    Button objBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objMainLL = (LinearLayout) findViewById(R.id.MainLayout);
        objSubLL = (LinearLayout) findViewById(R.id.LinearLayout2);
        objTV = (TextView) findViewById(R.id.textView);
        objBtn = (Button) findViewById(R.id.button);

        objBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int random1=new Random().nextInt(256);
                    int random2=new Random().nextInt(256);
                    int random3=new Random().nextInt(256);

                    int color= Color.rgb(random1,random2,random3);
                    objSubLL.setBackgroundColor(color);
                    objTV.setText("RGB("+random1+", "+random2+", "+random3+")");
            }
        });
    }
}