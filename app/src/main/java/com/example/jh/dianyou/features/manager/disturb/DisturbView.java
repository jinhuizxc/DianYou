package com.example.jh.dianyou.features.manager.disturb;

import com.example.jh.data.entity.TimeConfigEntity;
import com.example.jh.dianyou.view.LoadDataView;

import java.util.List;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

public interface DisturbView extends LoadDataView {
    void finishAddDisturb();
    void finishModifyDisturb();
    void setContantsList(List<TimeConfigEntity> timeConfigEntities);
}
