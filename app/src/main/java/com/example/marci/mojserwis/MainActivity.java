package com.example.marci.mojserwis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1000;
    final int MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS = 1001;
    final int MY_PERMISSIONS_RECORD_AUDIO = 1002;
    final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1003;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted, therefore prompt the user to grant permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }

        if (getApplicationContext().checkSelfPermission(Manifest.permission.PROCESS_OUTGOING_CALLS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted, therefore prompt the user to grant permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                    MY_PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS);
        }

        if (getApplicationContext().checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted, therefore prompt the user to grant permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    MY_PERMISSIONS_RECORD_AUDIO);
        }

        if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted, therefore prompt the user to grant permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
        }

        if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted, therefore prompt the user to grant permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
        }

        startService(new Intent(this,MyService.class));
        //CallReceiver callReceiver = new CallReceiver();
    }
}
