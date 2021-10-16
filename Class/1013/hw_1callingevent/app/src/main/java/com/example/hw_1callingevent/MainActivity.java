package com.example.hw_1callingevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText EditText;
    Button btn1, btn2;
    String num;
    String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        EditText=(EditText)findViewById(R.id.EditText1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=EditText.getText().toString();
                tel="tel:"+num;

                //현재 사용자의 os 버전이 마시멜로우인지 확인
                if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                    //사용자 단말기 권한 중 전화걸기 권한이 허용되어있는지 확인
                    int permissionResult=checkSelfPermission(Manifest.permission.CALL_PHONE);

                    //CALL PHONE의 권한이 없을 때
                    if(permissionResult== PackageManager.PERMISSION_DENIED){
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                            AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("권한이 필요합니다.").setMessage("이 기능을 사용하기 위해서는 전화걸기 권한이 필요합니다. 계속하시겠습니까?")
                                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
                                            }
                                        }
                                    })
                                    .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(MainActivity.this,"기능을 취소했습니다.",Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .create().show();
                        }
                        //최초로 권한 요청시
                        else{
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
                        }
                    }
                    //call_phone 권한이 있을 때
                    else{
                       startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                    }
                }
                //사용자 버전이 마시멜로우 이하일때
                else{
                    startActivity(new Intent("android.intent.action.CALL", Uri.parse(tel)));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=EditText.getText().toString();
                tel="tel:"+num;
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(tel));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
            } else {
                Toast.makeText(MainActivity.this, "권한요청을 거부하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


