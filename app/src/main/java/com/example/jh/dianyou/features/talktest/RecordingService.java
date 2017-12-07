package com.example.jh.dianyou.features.talktest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by jinhui on 2017/12/7.
 */

public class RecordingService extends Service {


    private static final String TAG = RecordingService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startRecording() {
        Log.e(TAG, "startRecording");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
