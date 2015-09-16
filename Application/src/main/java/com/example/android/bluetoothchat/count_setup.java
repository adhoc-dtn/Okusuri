package com.example.android.bluetoothchat;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by micoratocaster on 15/09/12.
 */
public class count_setup extends Activity{

    TimePicker tPicker;
    int notificationId;
    private PendingIntent alarmIntent;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        //buttonを取得
        Button btn1 = (Button)findViewById(R.id.num_one);
        btn1.setOnClickListener(clicked_one);

        Button btn2 = (Button)findViewById(R.id.num_two);
        btn2.setOnClickListener(clicked_two);

        Button btn3 = (Button)findViewById(R.id.num_three);
        btn3.setOnClickListener(clicked_three);

    }

    View.OnClickListener clicked_one= new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int number = 1;
            Intent setTimer = new Intent(count_setup.this, timer_setup.class);
            setTimer.putExtra("NUM_TAKE_MED", number);
            startActivity(setTimer);
        }
    };
    View.OnClickListener clicked_two= new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int number = 2;
            Intent setTimer = new Intent(count_setup.this, timer_setup.class);
            setTimer.putExtra("NUM_TAKE_MED", number);
            startActivity(setTimer);
        }
    };
    View.OnClickListener clicked_three= new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int number = 3;
            Intent setTimer = new Intent(count_setup.this, timer_setup.class);
            setTimer.putExtra("NUM_TAKE_MED", number);
            startActivity(setTimer);
        }
    };
}
