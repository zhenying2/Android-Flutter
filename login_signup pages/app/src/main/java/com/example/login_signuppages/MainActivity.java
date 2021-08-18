package com.example.login_signuppages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Path;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.os.AsyncTask;
import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String num;
    TextView te3;

    phpdo task;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = "송일감싸";
        String number="01072103429";

        task = new phpdo();
        //txtview = (TextView)findViewById(R.id.txtView);
        task.execute(name,number);

        //잠시만 기다려주세요
        TextView warn = (TextView)findViewById(R.id.warn);
        final AnimationSet warn_anim = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fadetext);
        warn.startAnimation(warn_anim );

        //섹션 돌리기
        Path path1=new Path();
        path1.addRect(100,400,1200,1350, Path.Direction.CCW);

        //글자 돌리기
        Path path2=new Path();
        path2.addRect(170,470,1350,1450, Path.Direction.CCW);


        //글자 감추기
        final AnimationSet textanim1 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);


        //1번 섹션
        ImageView sc1 = (ImageView)findViewById(R.id.section1);
        ObjectAnimator one = ObjectAnimator.ofFloat(sc1, View.X, View.Y, path1);
        one.setInterpolator(new LinearInterpolator());
        one.setDuration(8000);
        //1번 돌리기
        final AnimationSet anim1 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc1.startAnimation(anim1);
        one.setRepeatCount(-1);
        one.start();
        //1번 글자
        TextView te1 = (TextView)findViewById(R.id.text1);
        ObjectAnimator textone = ObjectAnimator.ofFloat(te1, View.X, View.Y, path2);
        textone.setInterpolator(new LinearInterpolator());
        textone.setDuration(8000);
        //글자 돌리기
        te1.startAnimation(textanim1);
        textone.setRepeatCount(-1);
        textone.start();




        //2번 섹션
        ImageView sc2 = (ImageView)findViewById(R.id.section2);
        ObjectAnimator two = ObjectAnimator.ofFloat(sc2, View.X, View.Y, path1);
        two.setInterpolator(new LinearInterpolator());
        two.setDuration(8000);
        //2번 돌리기
        final AnimationSet anim2 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc2.startAnimation(anim2);
        two.setStartDelay(1000);
        two.setRepeatCount(-1);
        two.start();
        //2번 글자
        TextView te2 = (TextView)findViewById(R.id.text2);
        ObjectAnimator texttwo = ObjectAnimator.ofFloat(te2, View.X, View.Y, path2);
        texttwo.setInterpolator(new LinearInterpolator());
        texttwo.setDuration(8000);
        //글자 돌리기
        te2.startAnimation(textanim1);
        texttwo.setStartDelay(1000);
        texttwo.setRepeatCount(-1);
        texttwo.start();

        //3번 섹션
        ImageView sc3 = (ImageView)findViewById(R.id.section3);
        ObjectAnimator three = ObjectAnimator.ofFloat(sc3, View.X, View.Y, path1);
        three.setInterpolator(new LinearInterpolator());
        three.setDuration(8000);
        //3번 돌리기
        final AnimationSet anim3 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc3.startAnimation(anim3);
        three.setStartDelay(2000);
        three.setRepeatCount(-1);
        three.start();
        //3번 글자
        //TextView te3 = (TextView)findViewById(R.id.text3);
        te3 = (TextView)findViewById(R.id.text3);
        ObjectAnimator textthree = ObjectAnimator.ofFloat(te3, View.X, View.Y, path2);
        textthree.setInterpolator(new LinearInterpolator());
        textthree.setDuration(8000);
        //글자 돌리기
        te3.startAnimation(textanim1);
        textthree.setStartDelay(2000);
        textthree.setRepeatCount(-1);
        textthree.start();

        //4번 섹션
        ImageView sc4 = (ImageView)findViewById(R.id.section4);
        ObjectAnimator four = ObjectAnimator.ofFloat(sc4, View.X, View.Y, path1);
        four.setInterpolator(new LinearInterpolator());
        four.setDuration(8000);
        //4번 돌리기
        final AnimationSet anim4 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc4.startAnimation(anim4);
        four.setStartDelay(3000);
        four.setRepeatCount(-1);
        four.start();
        //4번 글자
        TextView te4 = (TextView)findViewById(R.id.text4);
        ObjectAnimator textfour = ObjectAnimator.ofFloat(te4, View.X, View.Y, path2);
        textfour.setInterpolator(new LinearInterpolator());
        textfour.setDuration(8000);
        //글자 돌리기
        te4.startAnimation(textanim1);
        textfour.setStartDelay(3000);
        textfour.setRepeatCount(-1);
        textfour.start();

        //5번 섹션
        ImageView sc5 = (ImageView)findViewById(R.id.section5);
        ObjectAnimator five = ObjectAnimator.ofFloat(sc5, View.X, View.Y, path1);
        five.setInterpolator(new LinearInterpolator());
        five.setDuration(8000);
        //5번 돌리기
        final AnimationSet anim5 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc5.startAnimation(anim5);
        five.setStartDelay(4000);
        five.setRepeatCount(-1);
        five.start();
        //5번 글자
        TextView te5 = (TextView)findViewById(R.id.text5);
        ObjectAnimator textfive = ObjectAnimator.ofFloat(te5, View.X, View.Y, path2);
        textfive.setInterpolator(new LinearInterpolator());
        textfive.setDuration(8000);
        //글자 돌리기
        te5.startAnimation(textanim1);
        textfive.setStartDelay(4000);
        textfive.setRepeatCount(-1);
        textfive.start();

        //6번 섹션
        ImageView sc6 = (ImageView)findViewById(R.id.section6);
        ObjectAnimator six = ObjectAnimator.ofFloat(sc6, View.X, View.Y, path1);
        six.setInterpolator(new LinearInterpolator());
        six.setDuration(8000);
        //6번 돌리기
        final AnimationSet anim6 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc6.startAnimation(anim6);
        six.setStartDelay(5000);
        six.setRepeatCount(-1);
        six.start();
        //6번 글자
        TextView te6 = (TextView)findViewById(R.id.text6);
        ObjectAnimator textsix = ObjectAnimator.ofFloat(te6, View.X, View.Y, path2);
        textsix.setInterpolator(new LinearInterpolator());
        textsix.setDuration(8000);
        //글자 돌리기
        te6.startAnimation(textanim1);
        textsix.setStartDelay(5000);
        textsix.setRepeatCount(-1);
        textsix.start();

        //7번 섹션
        ImageView sc7 = (ImageView)findViewById(R.id.section7);
        ObjectAnimator seven = ObjectAnimator.ofFloat(sc7, View.X, View.Y, path1);
        seven.setInterpolator(new LinearInterpolator());
        seven.setDuration(8000);
        //7번 돌리기
        final AnimationSet anim7 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc7.startAnimation(anim7);
        seven.setStartDelay(6000);
        seven.setRepeatCount(-1);
        seven.start();
        //7번 글자
        TextView te7 = (TextView)findViewById(R.id.text7);
        ObjectAnimator textseven = ObjectAnimator.ofFloat(te7, View.X, View.Y, path2);
        textseven.setInterpolator(new LinearInterpolator());
        textseven.setDuration(8000);
        //글자 돌리기
        te7.startAnimation(textanim1);
        textseven.setStartDelay(6000);
        textseven.setRepeatCount(-1);
        textseven.start();

        //8번 섹션
        ImageView sc8 = (ImageView)findViewById(R.id.section8);
        ObjectAnimator eight = ObjectAnimator.ofFloat(sc8, View.X, View.Y, path1);
        eight.setInterpolator(new LinearInterpolator());
        eight.setDuration(8000);
        //8번 돌리기
        final AnimationSet anim8 = (AnimationSet) AnimationUtils.loadAnimation(this,R.anim.fade);
        sc8.startAnimation(anim8);
        eight.setStartDelay(7000);
        eight.setRepeatCount(-1);
        eight.start();
        //8번 글자
        TextView te8 = (TextView)findViewById(R.id.text8);
        ObjectAnimator textei = ObjectAnimator.ofFloat(te8, View.X, View.Y, path2);
        textei.setInterpolator(new LinearInterpolator());
        textei.setDuration(8000);
        //글자 돌리기
        te8.startAnimation(textanim1);
        textei.setStartDelay(7000);
        textei.setRepeatCount(-1);
        textei.start();


        /*setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신
            public void onDataReceived(byte[] data, String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            public void onDataReceived(byte[] data, String message) {


                String[] array = message.split(",");



            }
        });






        if (num==1){
            te1.setText("55975048");
        }*/

    }

    //알림창
    public void OnClickHandler(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("송일감싸").setMessage("자신의 수하물을 가지고 가셨습니까? 아니라면 전화연결 버튼을 눌러주세요. 공항으로 전화 연결됩니다.");

        builder.setPositiveButton("전화연결", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:1577-2600"));
                startActivity(myIntent);
            }
        });

        /*builder.setNegativeButton("인터넷연결", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(myIntent);

            }
        });*/

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "취소하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private class phpdo extends AsyncTask<String,Void,String> {

        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {
                String name = arg0[0];
                String number=arg0[1];

                String link = "https://zhenying2.cafe24.com/testquery.php?userName=" + name +"&userNumber=" + number;
                // String link = "http://192.168.220.1/testquery.php?userName=" + name +"&userNumber=" + number;

                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result){
            //txtview.setText("Login Successful");
            te3.setText(result);
            //3번 글자
        /*TextView te3 = (TextView)findViewById(R.id.text3);
        ObjectAnimator textthree = ObjectAnimator.ofFloat(te3, View.X, View.Y, path2);
        textthree.setInterpolator(new LinearInterpolator());
        textthree.setDuration(8000);
        //글자 돌리기
        te3.startAnimation(textanim1);
        textthree.setStartDelay(2000);
        textthree.setRepeatCount(-1);
        textthree.start();*/
        }
    }



}