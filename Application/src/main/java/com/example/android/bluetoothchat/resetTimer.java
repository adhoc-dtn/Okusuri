package com.example.android.bluetoothchat;


import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by micoratocaster on 15/09/12.
 */
public class resetTimer extends Activity{

    TimePicker tPicker;
    //int notificationId;
    //private PendingIntent alarmIntent;→グローバル化
    int position, print_count;
    TextView text;
    //グローバル変数
    Globals globals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //薬の服用回数を取得する
        Intent getter = getIntent();
        position= getter.getIntExtra("position",-1);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_take_med_time);

        //グローバル変数を取得
        globals = (Globals) this.getApplication();

        // TextViewに紐付け
        text = (TextView)findViewById(R.id.num_setting);
        print_count = position + 1;
        text.setText(print_count + "回目の服用時間を変更します");


        tPicker  =  (TimePicker)findViewById(R.id.timePicker);
        tPicker.setIs24HourView(true);
        Calendar setTimeCalendar = Calendar.getInstance();
        tPicker.setCurrentHour(setTimeCalendar.get(Calendar.HOUR_OF_DAY));

        Button startBtn = (Button)findViewById(R.id.start);

        startBtn.setOnClickListener(myAlarmListener);

        Resources res = getResources();
        int lightBlue = res.getColor(R.color.lightBlue);

        startBtn.setTextColor(lightBlue);
    }

    View.OnClickListener myAlarmListener= new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            /*Intent bootIntent = new Intent(timer_setup.this, AlarmBroadcastReceiver.class);
            bootIntent.putExtra("notificationId", notificationId);
            globals.alarmIntent = PendingIntent.getBroadcast(timer_setup.this, 0, bootIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

*/
            switch (v.getId()) {
                case R.id.start:

                    int hour = tPicker.getCurrentHour();
                    int minute = tPicker.getCurrentMinute();

                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY, hour);
                    startTime.set(Calendar.MINUTE, minute);
                    startTime.set(Calendar.SECOND, 0);
                    // 過去だったら明日にする
                    if(startTime.getTimeInMillis() < System.currentTimeMillis()){
                        startTime.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    long alarmStartTime = startTime.getTimeInMillis();

                    //アラームセットじゃオラ！！
                    globals.reChangeTimer(position, alarmStartTime);
                    //Toast.makeText(timer_setup.this, "セットした時刻の取得 : " + alarmStartTime, Toast.LENGTH_LONG).show();

                    // TextViewに紐付け
                    text = (TextView)findViewById(R.id.num_setting);
                    print_count = position + 1;
                    Toast.makeText(resetTimer.this, print_count + "回目の服用時刻を変更しました。", Toast.LENGTH_LONG).show();

                    finish();


            }


        }
    };
}
