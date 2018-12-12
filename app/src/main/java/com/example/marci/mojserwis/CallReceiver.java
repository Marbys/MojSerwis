package com.example.marci.mojserwis;

import android.app.Activity;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class CallReceiver extends PhonecallReceiver {
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = null;
    private Boolean isRecording = false;

    private MediaRecorder mRecorder;

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
        Log.d("Ingcoming call ended","x");
        //stopRecording();
        mRecorder.stop();
        //mRecorder.release();
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        Toast.makeText(ctx, "Starting Recording", Toast.LENGTH_LONG).show();
        mFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
        mFileName += "/audiorecordtest" + Calendar.getInstance().getTime().toString() + ".3gp";

        initRecorder();

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();

    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        mRecorder.stop();
        mRecorder.release();
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {

    }

    private void startRecording(Context ctx) {
        Log.d("Staring","Recording");

        Toast.makeText(ctx, "Starting Recording", Toast.LENGTH_LONG).show();
        mFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath();
        mFileName += "/audiorecordtest" + Calendar.getInstance().getTime().toString() + ".3gp";

        initRecorder();
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
        isRecording = true;
    }

    private void initRecorder() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    }

    private void stopRecording() {
        Log.d("Stoping","");
        try {
            Log.d("Trying","");
            if (mRecorder != null && isRecording) {
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                isRecording = false;
                Log.d("Stoping","Recorder");
            }
        } catch (Exception e) {
            Log.d("Catching","");
            mRecorder.stop();
            mRecorder.release();
            e.printStackTrace();
        }
    }
}
