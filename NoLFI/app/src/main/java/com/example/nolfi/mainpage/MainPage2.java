package com.example.nolfi.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nolfi.R;

import java.net.URI;

public class MainPage2 extends AppCompatActivity {
    ImageView product_image;

    //정보 연결
    Intent intent=getIntent();

    //store profile
    ImageView store_profile_image;
    TextView store_name, living_area;

    TextView product_name, product_category, product_detail, product_price;

    URI uri_storeprofileimage;
    String str_storename,str_livingarea,str_productname, str_productcategory, str_productdetail, str_productprice;

    @Override
    protected void onCreate(Bundle savedInstanveState){
        super.onCreate(savedInstanveState);
        setContentView(R.layout.fragment_mainpage2);

        product_image=findViewById(R.id.product_image_detail);
        store_profile_image=findViewById(R.id.store_profile_detail);
        store_name=findViewById(R.id.store_name_detail);
        living_area=findViewById(R.id.store_area_detail);
        product_name=findViewById(R.id.product_name_detail);
        product_category=findViewById(R.id.product_category_detail);
        product_detail=findViewById(R.id.content_detail);
        product_price=findViewById(R.id.product_price_detail);

        //받아온 extra 연결
        str_productname=intent.getExtras().getString("product_name");


    }

    public void displayStoreClick(View view){
        Intent intent = new Intent(this, MainPage3.class);
        startActivity(intent);
    }

    public void list_click_phone(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Contact").setMessage("010-1111-1111");

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}
