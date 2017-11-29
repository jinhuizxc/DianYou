package com.example.jh.dianyou.features.register;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jh.data.Constants;
import com.example.jh.data.entity.BlankEntity;
import com.example.jh.data.net.HttpMethods;
import com.example.jh.data.net.HttpResultFunc;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.features.login.UserComponent;
import com.example.jh.dianyou.utils.T;
import com.example.jh.dianyou.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/28.
 */

public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter, UserComponent> implements RegisterView {

    @BindView(R.id.et_username)
    EditText actvRegisterUsername;
    @BindView(R.id.et_password)
    EditText etRegisterPassword;
    @BindView(R.id.et_again_password)
    EditText etRegisterAgainPassword;
    @BindView(R.id.et_code)
    EditText editCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.btn_register)
    TextView btnRegister;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.textView3)
    TextView textView3;

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.title_register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void codeStop() {
        tvCode.setText(R.string.desc_get_code);
        tvCode.setEnabled(true);
        tvCode.setClickable(true);
    }

    @Override
    public void codeTurn(long time) {
        // 加判定?
//        if(etRegisterAgainPassword.getText().length() > 0 && etRegisterPassword.getText().toString()
//                .equals(etRegisterAgainPassword.getText().toString())){
        long second = time / 1000;
        tvCode.setText(second + getString(R.string.desc_second));
        if (second == 60) {
            tvCode.setText(59 + getString(R.string.desc_second));
        }

    }

    @Override
    public String getUsername() {
        return actvRegisterUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etRegisterPassword.getText().toString().trim();
    }

    @Override
    public String getAgainPassword() {
        return etRegisterAgainPassword.getText().toString().trim();
    }

    @Override
    public String getCode() {
        return editCode.getText().toString().trim();
    }

    @Override
    public void showPasswordError(String message) {
//        etRegisterPassword.setError(message);
        T.showShort(message);
        etRegisterPassword.requestFocus();
    }

    @Override
    public void showUsernameError(String message) {
//        actvRegisterUsername.setError(message);
        T.showShort(message);
        actvRegisterUsername.requestFocus();
    }

    @Override
    public void showAgainPasswordError(String message) {
//        etRegisterAgainPassword.setError(message);
        T.showShort(message);
        etRegisterAgainPassword.requestFocus();
    }

    @Override
    public void goToMain() {

    }

    @Override
    public void checkIsTrue(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getVerfy() {
        mPresenter.codeStart();
        tvCode.setEnabled(false);
        // 请求后台发送验证码
        HttpMethods.getInstance().getService().getverifycode(actvRegisterUsername.getText().toString(), Constants.APP_TOKEN)
                .map(new HttpResultFunc<BlankEntity>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<BlankEntity>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        T.showShort("服务器可能开了小差，导致获取验证码失败");
                    }

                    @Override
                    public void onNext(BlankEntity blankEntity) {
                        T.showShort("发送成功");
                    }
                });
    }

    @Override
    public void textNull(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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


    @OnClick({R.id.tv_code, R.id.et_again_password, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                // 信息一切正确才可以发送验证码
                mPresenter.check();
                editCode.requestFocus();
                break;
            case R.id.et_again_password:
                // setOnFocusChangeListener 获取焦点
                // addTextChangedListener 文本发生改变的监听                //   4.10号关于edittext焦点问题以及倒计时的问题
                etRegisterAgainPassword.setFocusable(true);

                break;
            case R.id.btn_register:
                mPresenter.attemptRegister();
                break;

        }
    }
}
