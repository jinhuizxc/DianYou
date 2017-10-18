package com.example.jh.dianyou.features.mine.my;


import com.example.jh.dianyou.view.LoadDataView;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface MineView extends LoadDataView {

    void updateView(String msg);

    void goLogin();

    void setUserName(String userLoginedUsername);

    void setNickName(String userLoginedNickname);

}
