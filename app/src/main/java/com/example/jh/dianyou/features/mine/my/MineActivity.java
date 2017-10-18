package com.example.jh.dianyou.features.mine.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.di.modules.ActivityModule;
import com.example.jh.dianyou.features.login.LoginActivity;
import com.example.jh.dianyou.utils.PreferencesUtils;
import com.example.jh.dianyou.view.activity.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/3.
 */

public class MineActivity extends BaseActivity<MineView, MinePresenter, MineComponent> implements MineView {

    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_devicelist)
    LinearLayout ll_devicelist;
    @BindView(R.id.ll_topUp)
    LinearLayout ll_topUp;
    @BindView(R.id.ll_activity)
    LinearLayout ll_activity;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    @Inject
    MinePresenter minePresenter;
    @BindView(R.id.tv_nick)
    TextView tvNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        //显示用户信息
//        mPresenter.showUserInfo();

        tvNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MineActivity.this, ModifyNickActivity.class));
            }
        });
    }

    @OnClick({R.id.tv_login, R.id.iv_photo, R.id.iv_setting, R.id.ll_topUp, R.id.ll_devicelist, R.id.ll_activity,R.id.iv_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
            case R.id.iv_photo:
                String token = PreferencesUtils.getString(MineActivity.this, "Token");
                if (token == null || token.equals("")) {
                    startActivity(new Intent(MineActivity.this, LoginActivity.class));
                } else {

                }
                break;
            case R.id.iv_setting:
//                startActivity(new Intent(MineActivity.this, SettingActivity.class));
                break;
            case R.id.ll_devicelist:
//                startActivity(new Intent(MineActivity.this, DeviceListActivity.class));
                break;
            case R.id.ll_topUp:
//                startActivity(new Intent(MineActivity.this, TopUpActivity.class));
                break;
            case R.id.ll_activity:
//                startActivity(new Intent(MineActivity.this, ActivityActivity.class));
                break;
            case R.id.iv_back:
                onBackPressed();
        }
    }

    @Override
    public void updateView(String msg) {
        tvLogin.setText(msg);
    }

    @Override
    public void goLogin() {
        Intent intent = new Intent(MineActivity.this, LoginActivity.class);
        startActivity(intent);
        MineActivity.this.finish();
    }

    @Override
    public void setUserName(String userLoginedUsername) {
        tvLogin.setText(userLoginedUsername);
    }

    @Override
    public void setNickName(String userLoginedNickname) {
        if (userLoginedNickname.isEmpty()) {
            tvNick.setText("某某某");
        } else {
            tvNick.setText(userLoginedNickname);
        }

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected MineComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().mineComponent(new ActivityModule(MineActivity.this));
    }

    @Override
    protected void injectDependencies(MineComponent component) {
        component.inject(this);
    }

    @Override
    protected String getToolbarTitle() {
        return null;
    }
}
