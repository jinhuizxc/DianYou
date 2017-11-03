package com.example.jh.dianyou.features.manager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.manager.disturb.DisturbActivity;
import com.example.jh.dianyou.view.activity.BaseActivity;
import com.example.jh.dianyou.view.dialog.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinhui on 2017/11/3.
 * Email：1004260403@qq.com
 */

public class ManagerActivity extends BaseActivity<ManagerView, ManagerPresenter, ManagerComponent> implements ManagerView {

    private String imei;

    @BindView(R.id.rl_watchmessage)
    RelativeLayout rlWatchmessage;
    @BindView(R.id.rl_contacts)
    RelativeLayout rlContacts;
    @BindView(R.id.rl_disturb)
    RelativeLayout rlDisturb;
    @BindView(R.id.rl_alarmclock)
    RelativeLayout rlAlarmclock;
    @BindView(R.id.rl_locationmode)
    RelativeLayout rlLocationmode;
    @BindView(R.id.rl_managersetting)
    RelativeLayout rlManagersetting;
    @BindView(R.id.rl_findwatch)
    RelativeLayout rlFindwatch;
    @BindView(R.id.rl_remoteclose)
    RelativeLayout rlRemoteclose;
    @BindView(R.id.rl_photoalbum)
    RelativeLayout rlPhotoalbum;
    @BindView(R.id.rl_sos)
    RelativeLayout rlSos;
    @BindView(R.id.btn_unbind)
    Button btnUnbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void unBindNext() {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected ManagerComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().managerComponent(getActivityModule());
    }

    @Override
    protected void injectDependencies(ManagerComponent component) {
        component.inject(this);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.manager);
    }

    @OnClick({R.id.rl_watchmessage, R.id.rl_contacts, R.id.rl_disturb, R.id.rl_alarmclock, R.id.rl_locationmode, R.id.rl_managersetting, R.id.rl_findwatch, R.id.rl_remoteclose, R.id.rl_photoalbum, R.id.rl_sos, R.id.btn_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_watchmessage:
                break;
            case R.id.rl_contacts:
                break;
            case R.id.rl_disturb:
                startActivity(new Intent(this, DisturbActivity.class).putExtra("imei", imei));
                break;
            case R.id.rl_alarmclock:
                break;
            case R.id.rl_locationmode:
                break;
            case R.id.rl_managersetting:
                break;
            case R.id.rl_findwatch:
                CustomDialog.Builder builder2 = new CustomDialog.Builder(this);
                builder2.setMessage("是否找设备?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 找设备
//                                mPresenter.seekdevice(imei);
                            }
                        });
                builder2.create().show();
                break;
            case R.id.rl_remoteclose:
                CustomDialog.Builder builder1 = new CustomDialog.Builder(this);
                builder1.setMessage("是否远程关机?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 远程关机
//                                mPresenter.shutdown(imei);
                            }
                        });
                builder1.create().show();
                break;
            case R.id.rl_photoalbum:
                break;
            case R.id.rl_sos:
                break;
            case R.id.btn_unbind:
                break;
        }
    }
}
