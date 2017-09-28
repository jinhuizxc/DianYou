package com.example.jh.dianyou.features.login;

import android.text.TextUtils;


import com.example.jh.base.YaRxPresenter;
import com.example.jh.data.PerActivity;


import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by flamingo on 2016/7/14.
 */

/**
 * // 每一个p层都必须要写这个构造方法才行
 * @Inject
 * LoginPresenter() {
 * }
 */
@PerActivity
public class LoginPresenter extends YaRxPresenter<LoginView> {



    @Inject
    LoginPresenter() {

    }


    public void attemptLogin() {

        getView().goToMain();
    }
}
