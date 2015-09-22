package com.example.android.bluetoothchat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.android.common.logger.Log;
/**
 * Created by micoratocaster on 15/09/19.
 */
public class startService extends Service {
    final static String TAG = "startService";
    final static int START_SERVICE = 1;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startld){
        Log.d(TAG, "onstartCommand");

        Intent startServ = new Intent(startService.this, startActivity.class);
        startActivity(startServ);
        return START_SERVICE;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();Log.d(TAG,"onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
