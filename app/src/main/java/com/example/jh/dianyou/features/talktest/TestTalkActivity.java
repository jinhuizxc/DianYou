package com.example.jh.dianyou.features.talktest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.talk.TalkComponent;
import com.example.jh.dianyou.features.talk.TalkPresenter;
import com.example.jh.dianyou.features.talk.TalkView;
import com.example.jh.dianyou.utils.T;
import com.example.jh.dianyou.view.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by jinhui on 2017/12/7.
 */

public class TestTalkActivity extends BaseActivity<TalkView, TalkPresenter, TalkComponent> implements TalkView, SwipeRefreshLayout.OnRefreshListener {


    private static final String TAG = TestTalkActivity.class.getSimpleName();
    TestAudioRecorderButton testAudioRecorderButton;
    private boolean mStartRecording = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_talktest);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);

        testAudioRecorderButton = (TestAudioRecorderButton) findViewById(R.id.id_recorder_button);
        testAudioRecorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort("短点击监听");

            }
        });
        testAudioRecorderButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                T.showShort("长点击监听");
                if (ActivityCompat.checkSelfPermission(TestTalkActivity.this, Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(TestTalkActivity.this, new String[]{Manifest.permission.RECORD_AUDIO},
                            10);
                } else {
                    // 开始录音
                    onRecord(mStartRecording);
                    Log.e(TAG, "开始录音1");
                    mStartRecording = !mStartRecording;
                }
                return false;
            }
        });
    }

    private void onRecord(boolean mStartRecording) {
        Intent intent = new Intent(this, RecordingService.class);
        Log.e(TAG, "intent =" + intent);

        startService(intent);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void setCheckedDeviceName(String checkedDeviceName) {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected TalkComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().talkComponent(getActivityModule());
    }

    @Override
    protected void injectDependencies(TalkComponent component) {
        component.inject(this);
    }

    @Override
    protected String getToolbarTitle() {
        return "测试语音对讲";
    }
}
