package com.example.jh.dianyou.features.local;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.adddevice.AddDeviceActivity;
import com.example.jh.dianyou.features.fencelist.FenceListActivity;
import com.example.jh.dianyou.features.history.HistoryActivity;
import com.example.jh.dianyou.features.manager.ManagerActivity;
import com.example.jh.dianyou.features.mine.my.MineActivity;
import com.example.jh.dianyou.utils.PreferencesUtils;
import com.example.jh.dianyou.utils.T;
import com.example.jh.dianyou.view.activity.BaseActivity;

import com.sloop.net.utils.NetUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/26.
 *
 * 定位、数据库 以及  compile 'fm.jiecao:jiecaovideoplayer:4.6.4' 节操视频播放器！
 *
 * 获取当前位置，
 * 获取手表实时位置
 *
 * 可以考虑数据库了！
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

    // 设备Listview
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
        setContentView(R.layout.activity_local);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        map.onCreate(savedInstanceState);// 此方法必须重写

        if (!NotificationManagerCompat.from(LocalActivity.this).areNotificationsEnabled()) {
            T.showShort("请允许通知，否则通知栏无法显示通知");
        }

        String token = PreferencesUtils.getString(this, "Token");
        if (token != null){
            Log.e(TAG, "token =" + token);
            System.out.println("token=" + token);
        }
        // 标题置为字体格式
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/mini.TTF");
        tvDevice.setTypeface(typeFace);
        // 设备列表
        View vPopupWindow = View.inflate(LocalActivity.this, R.layout.view_adddevice, null);
        lvDevice = (ListView) vPopupWindow.findViewById(R.id.lv_device);
        // 配置设备列表适配器——需要用到数据库来保存
        setupRecyclerView();

        pw = new PopupWindow(vPopupWindow, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        pw.setBackgroundDrawable(new ColorDrawable(0x00000000));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pw.setElevation(5f);
        }
        if(NetUtils.isNetConnection(this)){
            // 判定是否有设备，有设备获取定位信息等
            mPresenter.getDevice();
        }else {
            Toast.makeText(this, "网络连接异常，请检查网络是否连接！", Toast.LENGTH_SHORT).show();
        }


        // 请求定位权限，声明mLocationOption对象
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return;
        }

        // 位置监听方法
        mlocationClient.setLocationListener(this);
        mlocationClient.startLocation();
    }

    private void setupRecyclerView() {
//        lvDevice.setAdapter(adapter);
//        lvDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                pw.dismiss();
//                if (position == adapter.getList().size()) {
//                    startActivity(new Intent(LocalActivity.this, AddDeviceActivity.class));
//                } else {
//                    DeviceEn deviceEntity = adapter.getList().get(position);
//                    mPresenter.checkDevice(deviceEntity.imei());
//                    setDeviceName(deviceEntity.name());
//
//                }
//
//            }
//        });
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
        startActivity(new Intent(LocalActivity.this, AddDeviceActivity.class));
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
        if (count == 0) {
            this.noDevice();
        } else {
            pw.showAsDropDown(flToolbar, Gravity.END, 0, 0);
        }
    }

    @Override
    public void setPower(int power) {

    }

    @Override
    public void tiaozhaun() {
        startActivity(new Intent(LocalActivity.this, ManagerActivity.class));
    }

    @Override
    public void tiaozhaun(String imei) {
//        startActivity(new Intent(LocalActivity.this, ManagerActivity.class)
//                .putExtra("imei", imei));
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
    public void addlocationMap(double lat, double lng) {

    }

    @Override
    public void showLocation(double latitude, double longitude) {
        map.getMap().clear();
        LatLng latLng = new LatLng(latitude, longitude);
        Marker marker = map.getMap().addMarker(new MarkerOptions().draggable(false).position(latLng).icon(
                BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_marker_people)));
        marker.showInfoWindow();
        map.getMap().moveCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        latLng, 15, 0, 30)));
    }

    @Override
    public void showDeviceLocation(double latitude, double longitude) {
        map.getMap().clear();
        LatLng latLng = new LatLng(latitude, longitude);
        Marker marker = map.getMap().addMarker(new MarkerOptions().draggable(false).position(latLng).icon(
                BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_marker)));
        marker.showInfoWindow();
        map.getMap().moveCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        latLng, 15, 0, 30)));
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
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                //获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                System.out.println("/////" + aMapLocation.getLatitude() + "," + aMapLocation.getLongitude());
                Log.e(TAG, "onLocationChanged方法执行");
                // 显示当前位置
                showMap(aMapLocation.getLatitude(), aMapLocation.getLongitude());

            } else {
                Log.e(TAG, "显示错误信息");
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    private void showMap(double latitude, double longitude) {
        map.getMap().clear();
        LatLng latLng = new LatLng(latitude, longitude);
        Marker marker = map.getMap().addMarker(new MarkerOptions().draggable(false).position(latLng).icon(
                BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_marker)));
        marker.showInfoWindow();
        map.getMap().moveCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        latLng, 15, 0, 30)));
    }

    @OnClick({R.id.iv_history, R.id.iv_fence, R.id.iv_add_device, R.id.iv_phone, R.id.iv_setting, R.id.iv_talk, R.id.iv_photo, R.id.iv_record, R.id.iv_location
            , R.id.iv_goout, R.id.iv_manager, R.id.iv_message, R.id.iv_find, R.id.iv_reading_pen, R.id.iv_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_device:
                mPresenter.addDevice();
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
                startActivity(new Intent(LocalActivity.this, FenceListActivity.class));
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
                mPresenter.manager();
                break;
            case R.id.iv_phone:
                if (ActivityCompat.checkSelfPermission(LocalActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LocalActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
//                mPresenter.getPhoneNumber();
                break;

            // 无非就是现实自己的位置与手表的位置
            case R.id.iv_location:
//                if(prelongTime == 0){
//                    //第一次单击，初始化为本次单击的时间
//                    prelongTime = Long.parseLong(PreferencesUtils.CreateDate());
//                    mPresenter.getDeviceLocation();
//                }else {
//                    long curTime = Long.parseLong(PreferencesUtils.CreateDate());//本地单击的时间
//                    //System.out.println("两次单击间隔时间："+(curTime-prelongTime)+"当前时间是："+(curTime)+"第一次点击的时间是："+(prelongTime));//计算本地和上次的时间差
//                    if (curTime - prelongTime <= 60) {
//                        mPresenter.getLocation();
//                    } else {
//                        prelongTime = 0;//当前单击事件变为第一次点击的时间
//                    }
//                }
                // 得到我的位置
                mPresenter.getMylocation();
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


    /**
     * 管理隐藏布局
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && !ishide) {
            ObjectAnimator.ofFloat(llFlow, "translationX", -llFlow.getMeasuredWidth(), llFlow.getMeasuredWidth()).setDuration(500).start();
            ishide = true;
        }
        return super.dispatchTouchEvent(ev);
    }


    long waitTime = 2000;
    long touchTime = 0;
    // 再按一次退出应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                T.showShort(getString(R.string.tip_exit));
                touchTime = currentTime;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
