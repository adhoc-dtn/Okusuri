package com.example.android.bluetoothchat;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Globals extends Application {
    //アラームのリスト．キャンセルされると解除する
    ArrayList<alarmIdentifier> alarmList;
    //notification Id （使わないかも)
    private int notificationId;
    //タイマキャンセル時のセット済時刻との差分
    private int timeScope = 30;

    public void init(){
        alarmList = new ArrayList<alarmIdentifier>();
        notificationId = 0;
    }

    public void alarmAdd(int type, long alarmStartTime) {
        alarmIdentifier alarmId = new alarmIdentifier();
        //入力回数のカウント
        notificationId++;

        Intent bootIntent = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        bootIntent.putExtra("notificationId", notificationId);
        alarmId.alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmId.alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmId.startTime = alarmStartTime;
        //アラームをセットする．
        alarmId.alarm.set(
                type,
                alarmStartTime,
                alarmId.alarmIntent
        );
        //設定済のアラームのリストに加える
        alarmList.add(alarmId);
    }

    public boolean alarmCancel() {
        //現在時刻を取得(ミリ秒単位で表示)
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();

        //時刻に一番近いアラームを検索
        alarmIdentifier alarmInfo;
        for(int index=0; index < alarmList.size(); index++) {
            alarmInfo = alarmList.get(index);

            if (Math.abs(alarmInfo.startTime-currentTime) < timeScope*1000*60) {
                //アラームのキャンセル
                alarmInfo.alarm.cancel(alarmInfo.alarmIntent);
                //showToast("アラームはキャンセルされました");
                return true;
            }
        }
        //差分がX分以上有る場合は，間違いであるとみなす．キャンセルせず，警告メッセージを表示
        return false;

    }

    public void showToast(String text) {
        Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
    }
}

class alarmIdentifier {

    PendingIntent alarmIntent;
    //int notificationId;
    AlarmManager alarm;
    long startTime;

}