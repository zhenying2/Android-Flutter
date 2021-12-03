package com.example.part7_3_2_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivBattery;
    EditText edtBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배터리 상태 체크 (개선)");

        ivBattery=(ImageView) findViewById(R.id.ivBattery);
        edtBattery=(EditText) findViewById(R.id.edtBattery);
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter iFilter=new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,iFilter);
    }

    BroadcastReceiver br=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
                int remain=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                edtBattery.setText("현재 충전량 : "+remain+" %\n");

                if (remain >= 90) ivBattery.setImageResource(R.drawable.battery_100);
                else if (remain >=70) ivBattery.setImageResource(R.drawable.battery_80);
                else if (remain >= 50) ivBattery.setImageResource(R.drawable.battery_60);
                else ivBattery.setImageResource(R.drawable.battery_20);

                int plug=intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);

                switch (plug){
                    case 0:
                        edtBattery.append("전원 연결 : 안됨\n");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edtBattery.append("전원 연결 : 어댑터 연결됨\n");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edtBattery.append("전원 연결 : USB 연결됨\n");
                        break;
                }

                //battery 상태 출력
                int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
                switch (status){
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        Toast.makeText(getApplicationContext(),"배터리 상태: 현재 충전중임",Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        Toast.makeText(getApplicationContext(),"배터리 상태: 현재 충전중 아님",Toast.LENGTH_SHORT).show();
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        Toast.makeText(getApplicationContext(),"배터리 상태: 충전 100% 완료",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"배터리 상태: 상태 알 수 없음",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };
}