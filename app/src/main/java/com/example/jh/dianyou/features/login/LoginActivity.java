package com.example.jh.dianyou.features.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.local.LocalActivity;
import com.example.jh.dianyou.features.password.forget.ForgetPasswordActivity;
import com.example.jh.dianyou.features.register.RegisterActivity;
import com.example.jh.dianyou.utils.T;
import com.example.jh.dianyou.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/26.
 */

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter, UserComponent> implements LoginView {

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
        return actvLoginUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return etLoginPassword.getText().toString();
    }

    @Override
    public void showResult(String result) {
        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(LoginActivity.this, LocalActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showUsernameError(String message) {
        T.showShort(message);
        actvLoginUsername.requestFocus();
    }

    @Override
    public void showPasswordError(String message) {
        T.showShort(message);
//        etLoginPassword.setError(message);
        actvLoginUsername.requestFocus();
    }

    @Override
    public void cleanError() {
        actvLoginUsername.setError(null);
        etLoginPassword.setError(null);
    }

    @Override
    public void clearData() {
        actvLoginUsername.setText("");
        etLoginPassword.setText("");
    }

    @Override
    public Context context() {
        return this;
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forgetpasswprd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                mPresenter.attemptLogin();
                break;
            case R.id.tv_register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forgetpasswprd:
                intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
