package com.example.marci.mojserwis;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    private CallReceiver receiver;
    private String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"OnStartCommand Called!");
        receiver = new CallReceiver();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getApplicationContext().unregisterReceiver(receiver);
        Log.d(TAG, "onDestroy()");
    }
}
