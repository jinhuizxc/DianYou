package com.example.jh.dianyou.features.fencelist.fence;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.jh.data.device.DeviceEntity;
import com.example.jh.data.fence.FenceEntity;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.utils.T;
import com.example.jh.dianyou.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinhui on 2017/10/20.
 * Email：1004260403@qq.com
 * <p>
 * 项目安全区域出现bug急需解决！
 */

public class FenceActivity extends BaseActivity<FenceView, FencePresenter, FenceComponent> implements FenceView, PoiSearch.OnPoiSearchListener {


    private static final String TAG = FenceActivity.class.getSimpleName();
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
    @BindView(R.id.edit_address)
    EditText editAddress;


    private AMap aMap;
    Marker marker;
    Circle circle;

    // poi搜索
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private String city;
    private LatLng mFinalChoosePosition;
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    private List<PoiItem> poiItems;// poi数据
    // 创建数据链表
    private ArrayList<SearchAddressInfo> mData = new ArrayList<>();
    public SearchAddressInfo mAddressInfoFirst = null;

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.action_safefencesetting);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fence);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        map.onCreate(savedInstanceState);
        // 初始化地图对象
        init();
        seekBar.setMax(1000);
        mPresenter.showMyLocation();

        // 地图图层点击监听
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (marker != null) {
                    marker.destroy();
                }
                // 保证只有一个circle
                aMap.clear();
                circle = aMap.addCircle(new CircleOptions().center(latLng).radius(1000)
                        .strokeColor(
                                Color.TRANSPARENT)
                        .fillColor(Color.parseColor("#5555bce5")));

                marker = aMap.addMarker(new MarkerOptions().position(latLng).icon(
                        BitmapDescriptorFactory
                                .fromResource(R.mipmap.ic_marker_safe)).title("中心").draggable(true));
                /**
                 * 根据经纬度得到地址
                 * LatLng latLonPoint
                 */
                mFinalChoosePosition = latLng;
//                mPresenter.latlng2Address(latLng.latitude, latLng.longitude);
                // 开始进行poi搜索
                mPresenter.getAddressFromLonLat(latLng);
                doSearchQueryByPosition();
            }
        });
    }

    private void getAddressFromLonLat(LatLng latLng) {
    }

    /**
     * 开始进行poi搜索
     * 通过经纬度获取附近的poi信息
     * <p>
     * 1、keyword 传 ""
     * 2、poiSearch.setBound(new PoiSearch.SearchBound(lpTemp, 5000, true)); 根据
     */
    private void doSearchQueryByPosition() {
        currentPage = 0;
        query = new PoiSearch.Query("", "", city);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条position的item
        query.setPageNum(currentPage);// 设置查第一页

        LatLonPoint llPoint = convertToLatLonPoint(mFinalChoosePosition);
        if (llPoint != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);  // 实现  onPoiSearched  和  onPoiItemSearched
            poiSearch.setBound(new PoiSearch.SearchBound(llPoint, 5000, true));//
            // 设置搜索区域为以lpTemp点为圆心，其周围5000米范围
            poiSearch.searchPOIAsyn();// 异步搜索
        }

    }

    /**
     * 把LatLng对象转化为LatLonPoint对象
     */
    public static LatLonPoint convertToLatLonPoint(LatLng latlon) {
        return new LatLonPoint(latlon.latitude, latlon.longitude);
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
        editAddress.setText(address);
        // 条目中第一个地址 也就是当前你所在的地址
        mAddressInfoFirst = new SearchAddressInfo(address, address, false, convertToLatLonPoint(mFinalChoosePosition));
        Log.e(TAG, "第一个地址address = " + address);
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
        //
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


    @OnClick({R.id.iv_add_device, R.id.iv_location_device, R.id.iv_location_self, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_device:
                break;
            case R.id.iv_location_device:
                mPresenter.getDeviceLocation();
                break;
            case R.id.iv_location_self:
                mPresenter.getMyLocation();
                break;
            case R.id.btn_save:

                break;
        }
    }

    @Override
    public void onPoiSearched(PoiResult result, int rcode) {
        if (rcode == 1000) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始

                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                    //搜索到数据
                    if (poiItems != null && poiItems.size() > 0) {

                        Log.e(TAG, "搜索到数据: " + poiItems);
                        mData.clear();
                        //先将 逆地理编码过的当前地址 也就是条目中第一个地址 放到集合中
                        mData.add(mAddressInfoFirst);
                        SearchAddressInfo addressInfo = null;

                        for (PoiItem poiItem : poiItems) {
                            addressInfo = new SearchAddressInfo(poiItem.getTitle(), poiItem.getSnippet(), false, poiItem.getLatLonPoint());
                            mData.add(addressInfo);
                        }
                        mData.get(0).isChoose = true;

                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        T.showShort(FenceActivity.this,
                                "对不起，没有搜索到相关数据");
                    }
                }
            } else {
                Toast.makeText(this, "对不起，没有搜索到相关数据！！！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        T.showShort(FenceActivity.this, "infomation =" + infomation);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
