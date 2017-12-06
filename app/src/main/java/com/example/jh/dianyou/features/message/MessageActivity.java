package com.example.jh.dianyou.features.message;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.view.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by jinhui on 2017/12/5.
 */

public class MessageActivity extends BaseActivity<MessageView, MessagePresenter, MessageComponent> implements MessageView {


    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_info);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);



    }

    @Override
    public void showRead(int count) {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected MessageComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().messageComponent(getActivityModule());
    }

    @Override
    protected void injectDependencies(MessageComponent component) {
        component.inject(this);
    }


}
