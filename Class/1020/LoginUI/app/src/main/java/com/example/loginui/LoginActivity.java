package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView results;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView results=(TextView)findViewById(R.id.textView2);
        Button btn=(Button)findViewById(R.id.button2);

        Bundle extras=getIntent().getExtras();
        results.setText("ID = "+extras.getString("id")+"\nPassword = "+extras.getString("pw")+"\n");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent confirm=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(confirm);
            }
        });
    }
}