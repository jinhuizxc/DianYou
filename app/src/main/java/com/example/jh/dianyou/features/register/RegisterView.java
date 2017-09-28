package com.example.jh.dianyou.features.register;

import com.example.jh.dianyou.view.LoadDataView;

/**
 * Created by Administrator on 2017/9/28.
 */

public interface RegisterView extends LoadDataView {

    void codeStop();

    void codeTurn(long time);
    String getUsername();
    String getPassword();
    String getAgainPassword();
    String getCode();

    void showPasswordError(String message);

    void showUsernameError(String message);

    void showAgainPasswordError(String message);

    void goToMain();

    void checkIsTrue(String message);

    void getVerfy();    // 获取验证码的方法

    void textNull(String message); // 确保手机号、密码等信息不为空
}
