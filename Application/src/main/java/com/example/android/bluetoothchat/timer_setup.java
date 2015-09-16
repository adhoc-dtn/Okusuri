package com.example.android.bluetoothchat;



        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.provider.Settings;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import java.util.Calendar;

/**
 * Created by micoratocaster on 15/09/12.
 */
public class timer_setup extends Activity{

    TimePicker tPicker;
    //int notificationId;
    //private PendingIntent alarmIntent;→グローバル化
    int num_take_med, num_set_count = 0, print_count;
    TextView text;
    //グローバル変数
    Globals globals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //薬の服用回数を取得する
        Intent getter = getIntent();
        num_take_med= getter.getIntExtra("NUM_TAKE_MED",-1);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_take_med_time);

        //グローバル変数を取得
        globals = (Globals) this.getApplication();

        // TextViewに紐付け
        text = (TextView)findViewById(R.id.num_setting);
        print_count = num_set_count + 1;
        text.setText(print_count + "回目の服用時間を教えて下さい");


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
                    long alarmStartTime = startTime.getTimeInMillis();

                    globals.alarm.set(
                            AlarmManager.RTC_WAKEUP,
                            alarmStartTime,
                            globals.alarmIntent
                    );

                    globals.notificationId++;
                   //入力回数のカウント

                    // TextViewに紐付け
                    text = (TextView)findViewById(R.id.num_setting);
                    print_count = num_set_count + 1;
                    num_set_count++;
                    Toast.makeText(timer_setup.this, print_count + "回目の服用時刻を設定しました", Toast.LENGTH_SHORT).show();
                    text.setText(print_count + "回目の服用時間を教えて下さい");
                    if (num_set_count >= num_take_med) {
                        Toast.makeText(timer_setup.this, "すべての設定が完了しました！", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    //アラームのキャンセル
                    //alarm.cancel(alarmIntent);
                    break;

            }


        }
    };
}
