package com.example.hw_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    Button btn1;
    TextView tv_id,tv_pwd;
    String real_id,real_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView tv_id=(TextView)findViewById(R.id.tv2);
        TextView tv_pwd=(TextView)findViewById(R.id.tv3);

        Intent intent=getIntent(); //부른 intent를 받는다
        real_id=intent.getStringExtra("UserID");
        real_pwd=intent.getStringExtra("Password");

        tv_id.setText(real_id);
        tv_pwd.setText(real_pwd);

        btn1=(Button)findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SubActivity.this,MainActivity.class); //현재 화면의 제어권 넘어감
                startActivity(intent);
            }
        });
    }
}