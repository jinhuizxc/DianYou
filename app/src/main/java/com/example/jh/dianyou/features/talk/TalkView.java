package com.example.jh.dianyou.features.talk;

import com.example.jh.dianyou.view.LoadDataView;

import java.util.List;

/**
 * Created by jinhui on 2017/12/5.
 */

public interface TalkView extends LoadDataView {
    // 更新列表
//    void updateList(List<TalkEntitiy> talkEntitiys);

    void setCheckedDeviceName(String checkedDeviceName);
}