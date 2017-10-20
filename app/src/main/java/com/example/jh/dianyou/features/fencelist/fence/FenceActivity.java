package com.example.jh.dianyou.features.fencelist.fence;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.example.jh.data.device.DeviceEntity;
import com.example.jh.data.fence.FenceEntity;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.view.activity.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinhui on 2017/10/20.
 * Email：1004260403@qq.com
 * 项目安全区域出现bug急需解决！
 */

public class FenceActivity extends BaseActivity<FenceView, FencePresenter, FenceComponent> implements FenceView {


    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.iv_add_device)
    ImageView ivAddDevice;
    @BindView(R.id.iv_location_device)
    ImageView ivLocationDevice;
    @BindView(R.id.iv_location_self)
    ImageView ivLocationSelf;
    @BindView(R.id.btn_save)
    Button btnSave;

    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fence);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        map.onCreate(savedInstanceState);
        // 初始化地图对象
        init();
    }

    private void init() {
        if (aMap == null) {
            aMap = map.getMap();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();

    }


    @Override
    public Context context() {
        return this;
    }

    @Override
    public void showFenceName(String name) {

    }

    @Override
    public void showFenceDevice(String device) {

    }

    @Override
    public void showFenceAddress(String address) {

    }

    @Override
    public void showFenceLocation(FenceEntity fenceEntity) {

    }

    @Override
    public void showFenceRadius(String radius) {

    }

    @Override
    public void showLocation(double latitude, double longitude) {
        aMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).icon(
                BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_fence_people)).title("本机").draggable(false));
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(latitude, longitude), 15.5f, 0, 30)));
        seekBar.setProgress(1000);
    }

    @Override
    public void changeCheckedDevice(List<DeviceEntity> list) {

    }

    @Override
    public void getCheckedDeviceImei(String checkedImei) {

    }

    @Override
    public void addFendNext() {

    }

    @Override
    public void addOnMap(double lat, double lng) {

    }

    @Override
    protected FenceComponent initializeDi() {
        FenceComponent fencecomponent = AndroidApplication.getBaseApplication().getApplicationComponent().fenceComponent(getActivityModule());
        return fencecomponent;
    }

    @Override
    protected void injectDependencies(FenceComponent component) {
        component.inject(this);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.action_safefencesetting);
    }

    @OnClick({R.id.iv_add_device, R.id.iv_location_device, R.id.iv_location_self, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_device:
                break;
            case R.id.iv_location_device:
                break;
            case R.id.iv_location_self:
                mPresenter.getMyLocation();
                break;
            case R.id.btn_save:

                break;
        }
    }
}
