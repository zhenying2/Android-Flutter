package com.example.part7_3_event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public final static String MyAction="com.example.part7_3_event.ACTION_MY_BROADCAST";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())){
            Toast.makeText(context,"전원 연결",Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())){
            Toast.makeText(context,"전원 비연결",Toast.LENGTH_SHORT).show();
        }else if(MyAction.equals(intent.getAction())){
            Toast.makeText(context,"broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}