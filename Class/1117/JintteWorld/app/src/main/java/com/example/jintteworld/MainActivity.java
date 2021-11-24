package com.example.jintteworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("JintteWorld");

        final GridView gv=(GridView)findViewById(R.id.gridView01);
        MyGridAdapter gAdapter=new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
    }
}