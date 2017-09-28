package com.example.jh.dianyou.features.register;

import android.content.Context;
import android.os.Bundle;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.login.UserComponent;
import com.example.jh.dianyou.view.activity.BaseActivity;

/**
 * Created by Administrator on 2017/9/28.
 */

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter, UserComponent> implements RegisterView {


    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void codeStop() {

    }

    @Override
    public void codeTurn(long time) {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getAgainPassword() {
        return null;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public void showPasswordError(String message) {

    }

    @Override
    public void showUsernameError(String message) {

    }

    @Override
    public void showAgainPasswordError(String message) {

    }

    @Override
    public void goToMain() {

    }

    @Override
    public void checkIsTrue(String message) {

    }

    @Override
    public void getVerfy() {

    }

    @Override
    public void textNull(String message) {

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
