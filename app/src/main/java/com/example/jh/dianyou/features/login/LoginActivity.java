package com.example.jh.dianyou.features.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/26.
 */

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter, UserComponent> implements LoginView  {

    @BindView(R.id.actvLoginUsername)
    EditText actvLoginUsername;
    @BindView(R.id.etLoginPassword)
    EditText etLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forgetpasswprd)
    TextView tvForgetpasswprd;
    Intent intent;
    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_login);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 这个方法得放在super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected UserComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().userComponent(getActivityModule());
    }


    @Override
    protected void injectDependencies(UserComponent component) {
        component.inject(this);
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
    public void showResult(String result) {

    }

    @Override
    public void goToMain() {

    }

    @Override
    public void showUsernameError(String message) {

    }

    @Override
    public void showPasswordError(String message) {

    }

    @Override
    public void cleanError() {

    }

    @Override
    public void clearData() {

    }

    @Override
    public Context context() {
        return this;
    }
}
