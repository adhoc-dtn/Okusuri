package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micoratocaster on 15/09/26.
 */
public class change_timer extends Activity {
    //グローバル変数
    Globals globals;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //グローバル変数を取得
        globals = (Globals) getApplication();
        //現在セットされているタイマーを一覧に表示する
        List<String> timerList= globals.getTimer();

        super.onCreate(savedInstanceState);


        ListView lv = new ListView(this);

        setContentView(lv);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.timer_list, timerList) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextSize(25);
                return view;
            }
        };
        lv.setAdapter(adapter);

        //タイマー設定変更処理
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(change_timer.this, "position " + position,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), resetTimer.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }


}
