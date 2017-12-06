package com.example.jh.dianyou.features.talk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.talk.widget.AudioRecorderButton;
import com.example.jh.dianyou.features.talk.widget.RecoderAdapter;
import com.example.jh.dianyou.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jinhui on 2017/12/5.
 *
 * 关于允许语音权限的问题暂时放一放，把抖来抖去的问题解决
 */

public class TalkActivity extends BaseActivity<TalkView, TalkPresenter, TalkComponent> implements TalkView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = TalkActivity.class.getSimpleName();
    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    private ListView mListView;
    private RecoderAdapter mAdapter;
    private AudioRecorderButton mAudioRecorderButton;
    private View animView;
    private View animView1;
    private boolean isUpdate = true;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_talk);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_talk);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);


        mListView = (ListView) findViewById(R.id.id_listview);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        // 语音对讲的适配器暂时不做。
//        mAdapter = new RecoderAdapter(TalkActivity.this);
//        mListView.setAdapter(mAdapter);

//        // 允许录音的权限！
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
//                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//            return;
//        }

        // 开始录音
        mAudioRecorderButton.setFinishRecorderCallBack(new AudioRecorderButton.AudioFinishRecorderCallBack() {
            @Override
            public void onFinish(float seconds, String filePath) {
                Log.e(TAG, "录音完成方法执行");
            }
        });
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


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.e(TAG, "onRequestPermissionsResult 方法执行");
//                // Permission Granted
//                mAudioRecorderButton.setFinishRecorderCallBack(new AudioRecorderButton.AudioFinishRecorderCallBack() {
//
//                    public void onFinish(float seconds, String filePath) {
//
////                        long l = mPresenter.saveTheVoice((int) seconds, filePath);
////                        mPresenter.uploadData(filePath, l);
//                    }
//                });
//            } else {
//                // Permission Denied
//            }
//        }
//    }




}
