package com.example.hw_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText id, pwd;
    String ID,PWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id=(EditText)findViewById(R.id.edittext1);
        pwd=(EditText)findViewById(R.id.edittext2);

        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID=id.getText().toString();
                PWD=pwd.getText().toString();
                Intent intent=new Intent(MainActivity.this,SubActivity.class); //현재 화면의 제어권 넘어감
                intent.putExtra("UserID",ID);
                intent.putExtra("Password",PWD);
                startActivity(intent);
            }
        });
    }
}