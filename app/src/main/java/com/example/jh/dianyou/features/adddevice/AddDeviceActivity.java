package com.example.jh.dianyou.features.adddevice;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.adddevice.scan.ScanActivity;
import com.example.jh.dianyou.utils.T;
import com.example.jh.dianyou.view.activity.BaseActivity;
import com.example.jh.dianyou.view.adapter.NickAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jinhui on 2017/10/27.
 * Email：1004260403@qq.com
 */

public class AddDeviceActivity extends BaseActivity<AddDeviceView, AddDevicePresenter, AddDeviceComponent> implements AddDeviceView {


    @BindView(R.id.edit_imei)
    EditText editImei;
    @BindView(R.id.et_nick)
    EditText etNick;
    @BindView(R.id.et_request)
    EditText etRequest;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;

    @BindView(R.id.btn_commit_imei)
    Button btnCommitImei;
    @BindView(R.id.request_info)
    LinearLayout requestInfo;


    private String[] nick = {"爸爸", "妈妈", "爷爷", "奶奶", "叔叔", "阿姨", "其他"};

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_device_bind);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_adddevice);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }


    @Override
    protected AddDeviceComponent initializeDi() {
        return AndroidApplication.getBaseApplication().getApplicationComponent().addDeviceComponent(getActivityModule());
    }

    @Override
    protected void injectDependencies(AddDeviceComponent component) {
        component.inject(this);
    }

    @Override
    public void showRequestView() {

    }

    @Override
    public void showInfoLayout() {
        etRequest.setVisibility(View.GONE);
        requestInfo.setVisibility(View.VISIBLE);
        btnCommitImei.setText("确定");
    }

    @Override
    public void dismissRequest() {

    }

    @Override
    public void finishAddDevice() {

    }

    @Override
    public Context context() {
        return this;
    }

    @OnClick({R.id.iv_scan, R.id.iv_select_nick, R.id.btn_commit_imei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                startActivityForResult(new Intent(AddDeviceActivity.this, ScanActivity.class).putExtra("nick", etNick.getText().toString().trim()), 1);
                break;
            case R.id.iv_select_nick:
                final View vPopupWindow = View.inflate(AddDeviceActivity.this, R.layout.view_nick, null);
                final RecyclerView rv = (RecyclerView) vPopupWindow.findViewById(R.id.rv);
                final LinearLayout llInfo = (LinearLayout) vPopupWindow.findViewById(R.id.ll_info);
                final EditText name = (EditText) vPopupWindow.findViewById(R.id.et_name);

                rv.setLayoutManager(new GridLayoutManager(AddDeviceActivity.this, 4));
                final NickAdapter nickAdapter = new NickAdapter(AddDeviceActivity.this, nick);
                rv.setAdapter(nickAdapter);

                final PopupWindow pw = new PopupWindow(vPopupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                pw.setBackgroundDrawable(new ColorDrawable(0x00000000));
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                pw.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] - vPopupWindow.getHeight());
//                pw.showAtLocation(view, Gravity.NO_GRAVITY,0, 500);
                nickAdapter.setOnItemClickListener(new NickAdapter.OnItemClickListener() {
                    @Override
                    public void onImeiItemClicked(String nick, int position) {
                        if (nick.equals("其他")) {
                            rv.setVisibility(View.GONE);
                            llInfo.setVisibility(View.VISIBLE);
                            vPopupWindow.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String nickname = name.getText().toString().trim();
                                    if (nickname.length() > 4) {
                                        T.showShort("名称太长");
                                        return;
                                    } else if (nickname.isEmpty()) {
                                        T.showShort("名称不能为空");
                                        return;
                                    }
                                    etNick.setText(nickname);
                                    pw.dismiss();
                                }
                            });
                        } else {
                            etNick.setText(nick);
                            pw.dismiss();

                        }

                    }
                });
                break;
            case R.id.btn_commit_imei:
                if (btnCommitImei.getText().equals("添加")) {
                    if (editImei.getText().toString().trim().isEmpty()) {
                        T.showShort(getString(R.string.tip_input_imei));
                        return;
                    }
                    // 绑定设备
                    mPresenter.bindDevice(editImei.getText().toString().trim(), etNick.getText().toString().trim());
                } else if (btnCommitImei.getText().equals("请求")) {
                    if (editImei.getText().toString().trim().isEmpty()) {
                        T.showShort(getString(R.string.tip_input_imei));
                        return;
                    }
                    if (etRequest.getText().toString().trim().isEmpty()) {
                        T.showShort("请输入请求信息");
                        return;
                    }
                    // 发送绑定请求
//                    mPresenter.sendBindRquest(editImei.getText().toString().trim(),etRequest.getText().toString().trim());
                } else if (btnCommitImei.getText().equals("确定")) {
                    if (requestInfo.getVisibility() == View.VISIBLE) {
                        if (editImei.getText().toString().trim().isEmpty()) {
                            T.showShort(getString(R.string.tip_input_imei));
                            return;
                        }
                        if (etName.getText().toString().trim().isEmpty() || etNumber.getText().toString().trim().isEmpty()) {
                            T.showShort("请输入用户信息");
                            return;
                        }
                        // 设置设备信息
//                        mPresenter.setDeviceInfo(editImei.getText().toString().trim(),etName.getText().toString().trim(),etNumber.getText().toString().trim());
                    } else {
                        finish();
                    }

                }
                break;
        }
    }


}
