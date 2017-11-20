package com.example.jh.dianyou.features.history;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceOverlay;
import com.example.jh.data.entity.HistoryEntity;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.history.wheelview.ChangeDatePopwindow;
import com.example.jh.dianyou.view.activity.BaseActivity;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 1、轨迹纠偏
 * 2、3d图层添加纹理：
 * 3、自己绘制：就只能在两点之间加上箭头指向。点与点之间加上指向型箭头，从1->2->3->4
 */
public class HistoryActivity extends BaseActivity<HistoryView, HistoryPresenter, HistoryComponent> implements HistoryView, TraceListener {

    private static final String TAG = HistoryActivity.class.getSimpleName();
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.tv_history)
    LinearLayout layout;
    @BindView(R.id.tv_time)
    TextView tvTime;
    AMap aMap;
    @BindView(R.id.seek_bar1)
    SeekBar seekBar;
    @BindView(R.id.tv_time_bar)
    TextView tvTimeBar;

    private LatLng AllLatLng;
    // 起点
    private LatLng StartLatLng;
    private LatLng EndLatLng;

    Marker marker1;
    // 轨迹纠偏
    private List<TraceLocation> mTraceList = new ArrayList<TraceLocation>();
    private LBSTraceClient mTraceClient;
    private int mCoordinateType = LBSTraceClient.TYPE_AMAP;
    private ConcurrentMap<Integer, TraceOverlay> mOverlayList = new ConcurrentHashMap<Integer, TraceOverlay>();
    private int mSequenceLineID = 1000;


    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_history);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_history);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        seekBar.setMax(1440);
        map.onCreate(savedInstanceState);// 此方法必须重写

        aMap = map.getMap();
        Log.e(TAG, "aMap =" + aMap);
        // 加载历史轨迹
        mPresenter.showHistory();

        String time = tvTime.getText().toString();
        final String[] split = time.split("-");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] str = new String[10];
                final ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(HistoryActivity.this);
//                mChangeBirthDialog.setDate("2017", "6", "20");
//                mChangeBirthDialog.showAtLocation(frameLayout, Gravity.BOTTOM,0,0);
                mChangeBirthDialog.showAsDropDown(frameLayout);
                mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

                    @Override
                    public void onClick(String year, String month, String day) {

                    mPresenter.selectDate(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
                    }
                });

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mPresenter.changeMin(i);

                DisplayMetrics metric = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metric);
                int width = metric.widthPixels;     // 屏幕宽度（像素）

                int z = (seekBar.getWidth()) * i / 1440;
                int hour = i / 60;
                int min = i % 60;
                tvTimeBar.setText(hour + ":" + min);

                int progress = seekBar.getProgress();
                int max = seekBar.getMax();
                float percent = progress / (float) max;
                float setWidth = percent * width;
                // tvTimeBar.setX(setWidth);

                  int position = (seekBar.getWidth()) * i / 1440; //seekbar当前进度
                  float seekbarWidth = seekBar.getX(); //seekbar的宽度
                tvTimeBar.setX(setWidth);
        if ((hour>=0&&hour<19)){
            tvTimeBar.setBackgroundResource(R.mipmap.tv_time_bar_bg);
        }else
            tvTimeBar.setBackgroundResource(R.mipmap.tv_time_bar_bg_left);
                if (hour >= 0 && hour < 5) {
                    tvTimeBar.setX(setWidth + 55);
                } else if (hour >= 5 && hour < 7) {
                    tvTimeBar.setX(setWidth + 50);
                } else if (hour >= 7 && hour < 9) {
                    tvTimeBar.setX(setWidth + 43);
                } else if (hour >= 9 && hour < 12) {
                    tvTimeBar.setX(setWidth + 35);
                } else if (hour >= 12 && hour < 15) {
                    tvTimeBar.setX(setWidth + 25);
                } else if (hour >= 15 && hour < 17) {
                    tvTimeBar.setX(setWidth + 15);
                } else if (hour >= 17 && hour < 19) {
                    tvTimeBar.setX(setWidth + 10);
                } else {
                    if (hour >= 19 && hour <= 24) {
                        tvTimeBar.setX(setWidth-350);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
//                mPresenter.loadHistory();
            }
        });

//        text.layout(0, 20, seekBar.getWidth()/6, 150);

    }


    @Override
    protected HistoryComponent initializeDi() {
        HistoryComponent historyComponent = AndroidApplication.getBaseApplication().getApplicationComponent().historyComponent(getActivityModule());
        return historyComponent;
    }

    @Override
    protected void injectDependencies(HistoryComponent component) {
        component.inject(this);
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
    public void showDate(int year, int month, int date) {
        tvTime.setText(year + "-" + month + "-" + date);
    }


    @Override
    public void addOnMap(List<HistoryEntity> locationModels) {
        Log.e(TAG, "locationModels =" + locationModels);
        Observable.from(locationModels).map(new Func1<HistoryEntity, LatLng>() {
            @Override
            public LatLng call(HistoryEntity locationModel) {
                double lat = Double.parseDouble(locationModel.getLat());
                double lng = Double.parseDouble(locationModel.getLng());
                AllLatLng = new LatLng(lat, lng);

//                Log.e(TAG, "起点 =" + "" + locationModels.get(0).getLat());
                StartLatLng = new LatLng(Double.valueOf(locationModels.get(0).getLat()), Double.valueOf(locationModels.get(0).getLng()));
                // 画终点
                marker1 = map.getMap().addMarker(new MarkerOptions().draggable(false).position(StartLatLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_people)).title("终点"));
                marker1.showInfoWindow();

                // 画圈标识
                aMap.addCircle(new CircleOptions().center(AllLatLng)
                        .radius(Double.parseDouble("10"))
                        .strokeColor(Color.RED)
                        .fillColor(Color.parseColor("#f90303")));
//                Marker marker1 = map.getMap().addMarker(new MarkerOptions().draggable(false).position(AllLatLng)
//                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.circle)));
//                marker1.showInfoWindow();
                mTraceList.add(new TraceLocation(lat, lng, 1.0f, 1.0f, 1));
                Log.e(TAG, "加载点 =" + lat + lng);
                return new LatLng(lat, lng);
            }
        }).toList().subscribe(new Subscriber<List<LatLng>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<LatLng> latLngs) {

                //用一个数组来存放纹理
                List<BitmapDescriptor> texTuresList = new ArrayList<BitmapDescriptor>();
                texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_alr));
//                texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_custtexture));
//                texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.map_alr_night));

                //指定某一段用某个纹理，对应texTuresList的index即可, 四个点对应三段颜色
                List<Integer> texIndexList = new ArrayList<Integer>();
                texIndexList.add(0);//对应上面的第0个纹理
//                texIndexList.add(2);
//                texIndexList.add(1);

                PolylineOptions options = new PolylineOptions().addAll(latLngs).width(10).color(getResources().getColor(R.color.red));

                //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
                options.setCustomTextureList(texTuresList);
                //设置纹理对应的Index
                options.setCustomTextureIndex(texIndexList);

                aMap.addPolyline(options);
//                // 轨迹纠偏
//                traceGrasp();

                EndLatLng = latLngs.get(latLngs.size() - 1);
                // 画起点
                Marker marker2 = map.getMap().addMarker(new MarkerOptions().draggable(false).position(EndLatLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)).title("起点"));
                marker2.showInfoWindow();
                // 视角在终点  zoom: 15f  11f 越大位置越精确
//               最后一个点: 22.553333604601,113.94687608507
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        StartLatLng, 15f, 0, 30)));
            }
        });

    }

    private void traceGrasp() {
        Log.e(TAG, "轨迹纠偏方法执行");
        TraceOverlay mTraceOverlay = new TraceOverlay(aMap);
        mOverlayList.put(mSequenceLineID, mTraceOverlay);
        List<LatLng> mapList = traceLocationToMap(mTraceList);
        mTraceOverlay.setProperCamera(mapList);
        Log.e(TAG, "mapList =" + mapList.size());
        mTraceClient = new LBSTraceClient(HistoryActivity.this.getApplicationContext());
        mTraceClient.queryProcessedTrace(mSequenceLineID, mTraceList,
                mCoordinateType, this);
    }


    private List<LatLng> traceLocationToMap(List<TraceLocation> traceLocationList) {
        Log.e(TAG, "轨迹纠偏点转换为地图LatLng");
        List<LatLng> mapList = new ArrayList<LatLng>();
        for (TraceLocation location : traceLocationList) {
            LatLng latlng = new LatLng(location.getLatitude(),
                    location.getLongitude());
            mapList.add(latlng);
        }
        return mapList;
    }

    @Override
    public void setTimeProgress(int i) {

        seekBar.setProgress(i);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）

        int hour = i / 60;
        int min = i % 60;
        tvTimeBar.setText(hour + ":" + min);

        int progress = seekBar.getProgress();
        int max = seekBar.getMax();
        float percent = progress/(float)max;
        float setWidth = percent*width;
        tvTimeBar.setX(setWidth);
        if ((hour>=0&&hour<19)){
            tvTimeBar.setBackgroundResource(R.mipmap.tv_time_bar_bg);
        }else
            tvTimeBar.setBackgroundResource(R.mipmap.tv_time_bar_bg_left);
        if (hour >= 0 && hour < 5) {
            tvTimeBar.setX(setWidth + 55);
        } else if (hour >= 5 && hour < 7) {
            tvTimeBar.setX(setWidth + 50);
        } else if (hour >= 7 && hour < 9) {
            tvTimeBar.setX(setWidth + 43);
        } else if (hour >= 9 && hour < 12) {
            tvTimeBar.setX(setWidth + 35);
        } else if (hour >= 12 && hour < 15) {
            tvTimeBar.setX(setWidth + 25);
        } else if (hour >= 15 && hour < 17) {
            tvTimeBar.setX(setWidth + 15);
        }else if (hour >= 17 && hour < 19) {
            tvTimeBar.setX(setWidth + 10);
        } else if(hour >=19&&hour<=24){
            tvTimeBar.setX(setWidth-350);
        }

    }

    @Override
    public void resetProgress() {
        seekBar.setProgress(1440);
    }

    @Override
    public void clearMap() {
        aMap.clear();
        Log.e(TAG, "clearMap方法执行");
    }

    @Override
    public void showLocation(double latitude, double longitude) {
        map.getMap().clear();
        //坐标
        LatLng latLng = new LatLng(latitude, longitude);
        //标注
        Marker marker = map.getMap().addMarker(new MarkerOptions().draggable(false).position(latLng).icon(
                BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_marker_people)));
        //显示
        marker.showInfoWindow();
        //可视区域
        map.getMap().moveCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        latLng, 15, 0, 30)));
    }


    @Override
    public Context context() {
        return this;
    }


    @OnClick({R.id.btn_previous, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_previous:
                mPresenter.previous();
                break;
            case R.id.btn_next:
                mPresenter.next();
                break;
        }
    }

    /**
     * 轨迹纠偏失败回调
     */
    @Override
    public void onRequestFailed(int i, String s) {
        Log.e(TAG, "onRequestFailed");
    }

    /**
     * 轨迹纠偏过程回调
     */
    @Override
    public void onTraceProcessing(int lineID, int i1, List<LatLng> segments) {
        Log.e(TAG, "onTraceProcessing");
        if (segments == null) {
            return;
        }
        if (mOverlayList.containsKey(lineID)) {
            TraceOverlay overlay = mOverlayList.get(lineID);
            overlay.setTraceStatus(TraceOverlay.TRACE_STATUS_PROCESSING);
            overlay.add(segments);
        }
    }

    /**
     * 轨迹纠偏结束回调
     */
    @Override
    public void onFinished(int i, List<LatLng> list, int i1, int i2) {
        Log.e(TAG, "onFinished");
    }
}
