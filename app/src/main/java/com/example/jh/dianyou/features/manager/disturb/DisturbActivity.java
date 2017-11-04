package com.example.jh.dianyou.features.manager.disturb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.jh.data.entity.TimeConfigEntity;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.view.activity.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

public class DisturbActivity extends BaseActivity<DisturbView, DisturbPresenter, DisturbComponent> implements DisturbView {


    @BindView(R.id.iv_add_disturb)
    ImageView ivAddDisturb;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String imei = "000000000000000"; // 15个0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_disturb);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finishAddDisturb() {

    }

    @Override
    public void finishModifyDisturb() {

    }

    @Override
    public void setContantsList(List<TimeConfigEntity> timeConfigEntities) {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected DisturbComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().disturbComponent(getActivityModule());
    }

    @Override
    protected void injectDependencies(DisturbComponent component) {
        component.inject(this);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_disturb);
    }

    @OnClick({R.id.iv_add_disturb, R.id.recyclerView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_disturb:
                break;
            case R.id.recyclerView:
                break;
        }
    }
}
