package com.example.android.bluetoothchat;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class Globals extends Application {

    PendingIntent alarmIntent;
    int notificationId;
    AlarmManager alarm;
    public void init(){
        Intent bootIntent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        bootIntent.putExtra("notificationId", notificationId);
        alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

    }

    /*public void showToast(String text) {
        Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
    }*/
}
