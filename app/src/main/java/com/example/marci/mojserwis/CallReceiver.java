package com.example.marci.mojserwis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class CallReceiver extends PhonecallReceiver {
    private static String mFileName = null;
    private static Boolean isRecording = false;
    private static String TAG = "CallReceiver";
    private static MediaRecorder mRecorder = null;

    @Override
    protected void onIncomingCallReceived(Context ctx, String number, Date start) {
        Toast.makeText(ctx, "Cos dzwoni", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onIncomingCallAnswered(Context ctx, String number, Date start) {
        startRecording(ctx);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.d(TAG, "Ingcoming call ended");
        Log.d(TAG, "Trying: "+String.valueOf(isRecording) + String.valueOf(mRecorder != null));
        stopRecording(ctx);
        //mRecorder.stop();
        //mRecorder.release();
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
//        Toast.makeText(ctx, "Starting Recording", Toast.LENGTH_LONG).show();
//        mFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
//        mFileName += "/audiorecordtest" + Calendar.getInstance().getTime().toString() + ".3gp";
//
//        initRecorder();
//
//        try {
//            mRecorder.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mRecorder.start();

    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
//        mRecorder.stop();
//        mRecorder.release();
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {

    }

    private void initRecorder() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }

    private void startRecording(Context ctx) {
        //Log.d(TAG, "Staring Recording");
        if (isRecording) {
            try {
                mRecorder.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mRecorder.release();
        } else {
            //mFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
            mFileName = Environment.getExternalStorageDirectory() + java.io.File.separator +"MyRecord";
            mFileName += "/audiorecordtest" + "lala" + ".3gp";

            //Calendar.getInstance().getTime().toString()

            initRecorder();
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.d(TAG,"Error prepare()");
                //e.printStackTrace();
            }
            mRecorder.start();
            isRecording = true;
        }

    }

    private void stopRecording(Context ctx) {
        Log.d(TAG, "Stoping");
        try {
            Log.d(TAG, "Trying"+String.valueOf(isRecording) + String.valueOf(mRecorder != null));
            if (mRecorder != null && isRecording) {
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                isRecording = false;
                Log.d("Stoping", "Recorder");
            }
        } catch (Exception e) {
            Log.d(TAG, "Catching");
            mRecorder.stop();
            mRecorder.release();
            e.printStackTrace();
        }
        refreshSystemMediaScanDataBase(ctx,mFileName);
    }
    public static void refreshSystemMediaScanDataBase(Context context, String docPath){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(new File(docPath));
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }
}
