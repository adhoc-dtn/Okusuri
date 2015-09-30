/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


package com.example.android.bluetoothchat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.common.logger.Log;


/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class MainActivity extends FragmentActivity {

    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "MainActivity starts");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buttonを取得
        Button btn1 = (Button)findViewById(R.id.button_setting);

        btn1.setOnClickListener(setting);

        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState is null");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BluetoothChatFragment fragment = new BluetoothChatFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
       }

        try {
            Toast.makeText(MainActivity.this, "サービススタート",
                    Toast.LENGTH_SHORT).show();
            startService(new Intent(MainActivity.this, startService.class));
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "サービス起動しない．．．",
                    Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Could not starts startService", e);
        }

//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }

    View.OnClickListener setting= new View.OnClickListener() {

        @Override
        public void onClick(View v) {
//            Toast.makeText(MainActivity.this, "ボタンがおされました",
//                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, count_setup.class);
            startActivity(intent);
        }
    };
}
