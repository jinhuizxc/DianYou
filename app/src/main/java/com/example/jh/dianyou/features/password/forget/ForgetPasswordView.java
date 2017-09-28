package com.example.jh.dianyou.features.password.forget;

import com.example.jh.base.YaView;
import com.example.jh.dianyou.view.LoadDataView;

/**
 * Created by Administrator on 2017/9/28.
 */

public interface ForgetPasswordView extends LoadDataView {

    String getUsername();
    void showUsernameError(String message);
    void goReset();
}
