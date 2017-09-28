package com.example.jh.dianyou.features.password.forget;

import android.content.Context;
import android.os.Bundle;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.login.UserComponent;
import com.example.jh.dianyou.view.activity.BaseActivity;

/**
 * Created by Administrator on 2017/9/28.
 */

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordView, ForgetPasswordPresenter, UserComponent> implements ForgetPasswordView {

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.reset_password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forgetpassword);
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void showUsernameError(String message) {

    }

    @Override
    public void goReset() {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected UserComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().userComponent(getActivityModule());
    }

    @Override
    protected void injectDependencies(UserComponent component) {
        component.inject(this);
    }


}
