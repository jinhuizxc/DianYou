package com.example.jh.dianyou.features.local;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.MapView;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.history.HistoryActivity;
import com.example.jh.dianyou.features.mine.my.MineActivity;
import com.example.jh.dianyou.utils.PreferencesUtils;
import com.example.jh.dianyou.utils.T;
import com.example.jh.dianyou.view.activity.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/26.
 */

public class LocalActivity extends BaseActivity<LocalView, LocalPresenter, LocalComponent> implements LocalView, AMapLocationListener {

    private static final String TAG = LocalActivity.class.getSimpleName();
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.iv_power)
    ImageView ivPower;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_device)
    TextView tvDevice;

    // 设备listview
    ListView lvDevice;
    //    @Inject
//    DeviceListAdapter adapter;
    @BindView(R.id.fl_toolbar)
    FrameLayout flToolbar;

    // PopupWindow
    PopupWindow pw;

    @BindView(R.id.ll_flow)
    LinearLayout llFlow;

    @Inject
    AMapLocationClient mlocationClient;

    @BindView(R.id.tv_talk_num)
    TextView tvTalkNum;
    @BindView(R.id.tv_message_num)
    TextView tvMessageNum;
    private boolean ishide = true;
    long prelongTime = 0;

    @Override
    protected String getToolbarTitle() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        map.onCreate(savedInstanceState);// 此方法必须重写
        mlocationClient.setLocationListener(this);
        mlocationClient.startLocation();

    }


    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);

    }

    @Override
    protected LocalComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().localComponent(getActivityModule());
    }

    @Override
    protected void injectDependencies(LocalComponent component) {
        component.inject(this);
    }

    @Override
    public void noDevice() {

    }

    @Override
    public void setDeviceName(String name) {

    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setTime(String s) {

    }

    @Override
    public void initLocation() {

    }

    @Override
    public void addDeviceStatus(int count) {

    }

    @Override
    public void setPower(int power) {

    }

    @Override
    public void tiaozhaun(String imei) {

    }

    @Override
    public void showRead(int count) {

    }

    @Override
    public void showMessageRead(int count) {

    }

    @Override
    public void setPhoneNumber(String phone) {

    }

    @Override
    public void showCallResult() {

    }

    @Override
    public void showTakePhotoResult() {

    }

    @Override
    public void addOnMap(double lat, double lng) {

    }

    @Override
    public void addlocationMap(double lat, double lng) {

    }

    @Override
    public void showLocation(double latitude, double longitude) {

    }

    @Override
    public void showDeviceLocation(double latitude, double longitude) {

    }

    @Override
    public Context context() {
        return this;
    }

    /**
     * 重写地图的onLocationChanged方法
     *
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @OnClick({R.id.iv_history, R.id.iv_fence, R.id.iv_add_device, R.id.iv_phone, R.id.iv_setting, R.id.iv_talk, R.id.iv_photo, R.id.iv_record, R.id.iv_location
            , R.id.iv_goout, R.id.iv_manager, R.id.iv_message, R.id.iv_find, R.id.iv_reading_pen})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_device:
//                mPresenter.addDevice();
                return;
            case R.id.iv_setting:
                if (ishide) {
                    ObjectAnimator.ofFloat(llFlow, "translationX", 0, -llFlow.getMeasuredWidth()).setDuration(500).start();
                    ishide = false;
                } else {
                    ObjectAnimator.ofFloat(llFlow, "translationX", -llFlow.getMeasuredWidth(), llFlow.getMeasuredWidth()).setDuration(500).start();
                    ishide = true;
                }
                return;
            case R.id.iv_me:
                startActivity(new Intent(LocalActivity.this, MineActivity.class));
                return;
        }
        /**
         * 在进入下面的界面需要绑定设备！
         */
//        if (mPresenter.getDeviceCount() == 0) {
//            T.showShort("需要先绑定设备");
//            return;
//        }
//        if (!mPresenter.getDeviceIsChecked()) {
//            T.showShort("请选择设备");
//            return;
//        }

        switch (view.getId()) {
            case R.id.iv_history:
                startActivity(new Intent(LocalActivity.this, HistoryActivity.class));
                break;
            case R.id.iv_fence:
//                startActivity(new Intent(LocalActivity.this, FenceListActivity.class));
                break;
            case R.id.iv_record:
//                mPresenter.record();
//                startActivity(new Intent(getActivity(), RecordActivity.class));
                break;
            case R.id.iv_talk:
//                startActivity(new Intent(LocalActivity.this, TalkActivity.class));
                break;
            case R.id.iv_photo:
                //startActivity(new Intent(getActivity(), TakePhotoActivity.class));
//                mPresenter.takephoto();
                break;
            case R.id.iv_manager:
//                mPresenter.just();
                break;
            case R.id.iv_phone:
                if (ActivityCompat.checkSelfPermission(LocalActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LocalActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
//                mPresenter.getPhoneNumber();
                break;

            case R.id.iv_location:
                // 调用接口，如果短时间内再按就回到定位的位置
                if (prelongTime == 0) {//第一次单击，初始化为本次单击的时间
                    prelongTime = Long.parseLong((new PreferencesUtils()).CreateDate());
                    Log.e(TAG, "prelongTime = " + prelongTime);
//                    mPresenter.getDeviceLocation();
//                    mPresenter.test();
                } else {
                    long curTime = Long.parseLong(new PreferencesUtils().CreateDate());//本地单击的时间
                    //System.out.println("两次单击间隔时间："+(curTime-prelongTime)+"当前时间是："+(curTime)+"第一次点击的时间是："+(prelongTime));//计算本地和上次的时间差
                    if (curTime - prelongTime <= 600) {
//                        mPresenter.getLocation();
                        Log.e(TAG, "prelongTime11111 =" + prelongTime);
                    } else {
                        prelongTime = 0;//当前单击事件变为第一次点击的时间
                    }
                }
                break;
            case R.id.iv_message:
//                startActivity(new Intent(LocalActivity.this, MessageActivity.class));
                break;
            case R.id.iv_find:
//                startActivity(new Intent(LocalActivity.this, FindActivity.class));
                break;
            case R.id.iv_goout:
//                startActivity(new Intent(LocalActivity.this, MacScanActivity.class));
                break;

            case R.id.iv_reading_pen:
//                startActivity(new Intent(LocalActivity.this, ReadingBookActivity.class));
                break;
        }
    }
}
