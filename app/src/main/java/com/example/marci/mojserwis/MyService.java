package com.example.marci.mojserwis;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    CallReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        receiver = new CallReceiver();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        receiver = null;
    }
}
