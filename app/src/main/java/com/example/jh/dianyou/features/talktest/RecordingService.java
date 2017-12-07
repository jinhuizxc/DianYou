package com.example.jh.dianyou.features.talktest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.jh.dianyou.features.talk.widget.AudioManager;

/**
 * Created by jinhui on 2017/12/7.
 */

public class RecordingService extends Service {

    //录音管理工具类
    private AudioManager mAudioManager;

    private static final String TAG = RecordingService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand————————>");
        startRecording();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startRecording() {
        // 配置录音配置
        mAudioManager = AudioManager.getInstance();
        mAudioManager.prepareAudio();
        Log.e(TAG, "startRecording");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
