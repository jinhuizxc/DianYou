package com.example.jh.dianyou.features.talk.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.jh.dianyou.R;

/**
 * Created by jinhui on 2017/12/5.
 * 自定义Button
 */

public class AudioRecorderButton extends AppCompatButton {

    private static final String TAG = AudioRecorderButton.class.getSimpleName();
    // 按钮正常状态（默认状态）
    private static final int STATE_NORMAL = 1;
    //正在录音状态
    private static final int STATE_RECORDING = 2;
    //录音取消状态
    private static final int STATE_CANCEL = 3;
    //记录当前状态
    private int mCurrentState = STATE_NORMAL;
    //是否开始录音标志
    private boolean isRecording = false;
    //判断在Button上滑动距离，以判断 是否取消
    private static final int DISTANCE_Y_CANCEL = 50;
    //对话框管理工具类
    private DialogManager mDialogManager;
    //录音管理工具类
    private AudioManager mAudioManager;
    //记录录音时间
    private float mTime;
    // 是否触发longClick
    private boolean mReady;
    //录音准备
    private static final int MSG_AUDIO_PREPARED = 0x110;
    //音量发生改变
    private static final int MSG_VOICE_CHANGED = 0x111;
    //取消提示对话框
    private static final int MSG_DIALOG_DIMISS = 0x112;
    private static final int MSG_LONG = 0x113;
    Context context;

    /**
     * @description 获取音量大小的线程
     * @author ldm
     * @time 2016/6/25 9:30
     * @param
     */
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        @Override
        public void run() {
            // 判断正在录音
            while (isRecording){
                try {
                    Thread.sleep(1000);
                    mTime += 0.1f;//录音时间计算
                    Log.e(TAG, "mTime =" + mTime);
                    if(mTime > 15f) {
                        mHandler.sendEmptyMessage(MSG_LONG);
                    }
                    mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);//每0.1秒发送消息
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                // 准备录音
                case MSG_AUDIO_PREPARED:
                    //显示对话框
                    mDialogManager.showRecordingDialog();
                    isRecording = true;
                    Log.e(TAG, "isRecording =" + isRecording);
                    // 开启一个线程计算录音时间
                    new Thread(mGetVoiceLevelRunnable).start();
                    break;
                // 音量发生改变
                case MSG_VOICE_CHANGED:
                    //更新声音
                    mDialogManager.updateVoiceLevel(mAudioManager.getVoiceLevel(7));
                    break;
                // 取消对话框
                case MSG_DIALOG_DIMISS:
                    mDialogManager.dimissDialog();
                    break;
                case MSG_LONG:
                    break;
            }
        }
    };

    public AudioRecorderButton(Context context) {
        super(context);
    }

    public AudioRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        mDialogManager = new DialogManager(context);
        //录音文件存放地址

        mAudioManager = AudioManager.getInstance();
        mAudioManager.setOnAudioStateListener(new AudioManager.AudioStateListener() {
            public void wellPrepared() {
                mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
            }
        });

        // 由于这个类是button所以在构造方法中添加监听事件
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mReady = true;
                // 准备工作
                mAudioManager.prepareAudio();
                Log.e(TAG, "prepareAudio 方法执行");
                return false;
            }
        });
    }

    public AudioRecorderButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取TouchEvent状态
        int action = event.getAction();
        // 获得x轴坐标
        int x = (int) event.getX();
        // 获得y轴坐标
        int y = (int) event.getY();

        switch (action){
            // 手指按下
            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDING);
                break;
            // 手指移动
            case MotionEvent.ACTION_MOVE:
                break;
            // 手指抬起
            case MotionEvent.ACTION_UP:
                if (!mReady) {
                    reset();
                    return super.onTouchEvent(event);
                }
                //如果时间少于0.6s，则提示录音过短
                if(!isRecording || mTime < 0.4f){
                    mDialogManager.tooShort();
                    mAudioManager.cancel();
                    // 延迟显示对话框
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1000);
                }else if(mCurrentState == STATE_RECORDING) {
                    Log.e(TAG, "mCurrentState == 正在录音");
//                    //如果状态为正在录音，则结束录制
                    mDialogManager.dimissDialog();
                    mAudioManager.release();
                    if (finishRecorderCallBack != null) {
                        finishRecorderCallBack.onFinish(mTime, mAudioManager.getCurrentFilePath());
                    }
                }else if(mCurrentState == STATE_CANCEL){
                    Log.e(TAG, "mCurrentState == 取消");
                    // 想要取消
//                    mDialogManager.dimissDialog();
//                    mAudioManager.cancel();
                }
                reset();
                break;

        }

        return super.onTouchEvent(event);
    }

    /**
     * 恢复状态及标志位
     */
    private void reset() {
        isRecording = false;
        mTime = 0;
        mReady = false;
        changeState(STATE_NORMAL);
    }

    /**
     * @param
     * @description 根据状态改变Button显示
     * @author ldm
     * @time 2016/6/25 9:36
     */
    private void changeState(int state) {
        if(mCurrentState != state){
            mCurrentState = state;
            switch (state){
                // 正常状态
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder);
                    break;
                // 正在录音
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.play_recorder);
                    AnimationDrawable animationDrawable = (AnimationDrawable) getBackground();
                    animationDrawable.start();
                    if (isRecording) {
                        mDialogManager.recording();
                        Log.e(TAG, "mDialogManager.recording() 方法执行");
                    }
                    break;
                case STATE_CANCEL:
                    setBackgroundResource(R.drawable.btn_recorder_stop);
                    mDialogManager.wantToCancel();
                    break;
            }
        }
    }

    // 录音完成后的回调
    public interface AudioFinishRecorderCallBack {
        void onFinish(float seconds, String filePath);
    }

    private AudioFinishRecorderCallBack finishRecorderCallBack;

    public void setFinishRecorderCallBack(AudioFinishRecorderCallBack finishRecorderCallBack) {
        this.finishRecorderCallBack = finishRecorderCallBack;
    }
}
