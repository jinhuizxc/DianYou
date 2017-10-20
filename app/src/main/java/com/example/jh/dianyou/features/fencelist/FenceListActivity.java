package com.example.jh.dianyou.features.fencelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.jh.data.fence.FenceEntity;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.fencelist.fence.FenceActivity;
import com.example.jh.dianyou.view.activity.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinhui on 2017/10/20.
 * Email：1004260403@qq.com
 */

public class FenceListActivity extends BaseActivity<FendListView, FenceListPresenter, FenceListComponent> implements FendListView {


    @BindView(R.id.btn_add_safety_zone)
    Button btnAddSafetyZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fend_list);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showFendList(List<FenceEntity> fendList) {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected FenceListComponent initializeDi() {
        FenceListComponent fenceListComponent = AndroidApplication.getBaseApplication().getApplicationComponent().fenceListComponent(getActivityModule());
        return fenceListComponent;
    }

    @Override
    protected void injectDependencies(FenceListComponent component) {
        component.inject(this);
    }

    @Override
    protected String getToolbarTitle() {
        return null;
    }

    @OnClick(R.id.btn_add_safety_zone)
    public void onViewClicked() {
        startActivity(new Intent(FenceListActivity.this, FenceActivity.class));
    }
}
