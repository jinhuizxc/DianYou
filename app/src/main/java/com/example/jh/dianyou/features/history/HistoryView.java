package com.example.jh.dianyou.features.history;



import com.example.jh.data.location.LocationEntity;
import com.example.jh.dianyou.view.LoadDataView;

import java.util.List;

/**
 * Created by fflamingogo on 2016/7/22.
 * 测试自己的分支!
 *
 */
public interface HistoryView extends LoadDataView {

    void showDate(int year, int month, int date);

    void addOnMap(List<LocationEntity> locationModels);

    void setTimeProgress(int min);


    void resetProgress();

    void clearMap();

    void showLocation(double latitude, double longitude);
}
