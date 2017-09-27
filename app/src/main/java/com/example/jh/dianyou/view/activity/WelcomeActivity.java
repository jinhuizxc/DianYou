package com.example.jh.dianyou.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.local.LocalActivity;
import com.example.jh.dianyou.features.login.LoginActivity;
import com.example.jh.dianyou.utils.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/25.
 * 从今天起开始项目重新学习！
 * 目前形势严峻，需要重新出发，把这个项目做完可以考虑转型！
 */

public class WelcomeActivity extends NormalActivity {


    @BindView(R.id.tv_welcome_1)
    TextView tvWelcome1;
    @BindView(R.id.tv_welcome_2)
    TextView tvWelcome2;

    @Override
    protected String getToolbarTitle() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Typeface typeFace1 = Typeface.createFromAsset(getAssets(), "fonts/mini.TTF");
        tvWelcome1.setTypeface(typeFace1);
        Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/mini.TTF");
        tvWelcome2.setTypeface(typeFace2);
        // 写一个线程
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = PreferencesUtils.getString(WelcomeActivity.this, "Token");
                // 新用户的token为""
                if (token == null || token.equals("")) {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LocalActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 2000);
    }
}
