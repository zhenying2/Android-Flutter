package com.example.nolfi.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nolfi.R;


public class MainPage1 extends AppCompatActivity {

    boolean i=true;
    ImageView imageview = null;
    @Override
    protected void onCreate(Bundle savedInstanveState){
        super.onCreate(savedInstanveState);

        imageview = (ImageView)findViewById(R.id.scrab);
    }
    public void displayListClick(View view){
        Intent intent = new Intent(this, MainPage2.class);
        startActivity(intent);
    }
    public void scrabClick(View view){
        if (i==true){
            imageview.setImageResource(R.drawable.scrab);
            i=false;
        }
        else{
            imageview.setImageResource(R.drawable.cl_scrab);
            i=true;
        }
    }
}