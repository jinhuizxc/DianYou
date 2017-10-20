package com.example.jh.dianyou.features.fencelist;

import com.example.jh.data.fence.FenceEntity;
import com.example.jh.dianyou.view.LoadDataView;

import java.util.List;

/**
 * Created by jinhui on 2017/10/20.
 * Email：1004260403@qq.com
 */

public interface FendListView extends LoadDataView {

    void showFendList(List<FenceEntity> fendList);
}
