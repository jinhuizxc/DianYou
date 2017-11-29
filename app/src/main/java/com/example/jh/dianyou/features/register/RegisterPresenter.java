package com.example.jh.dianyou.features.register;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;

import com.example.jh.base.YaRxPresenter;
import com.example.jh.data.Constants;
import com.example.jh.data.PerActivity;
import com.example.jh.data.ProgressSubscriber;
import com.example.jh.data.SubscriberOnNextListener;
import com.example.jh.data.entity.BlankEntity;
import com.example.jh.data.user.TokenEntity;
import com.example.jh.data.user.UserRepo;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.utils.MD5Util;
import com.example.jh.dianyou.utils.PreferencesUtils;

import javax.inject.Inject;

import cn.jpush.android.api.JPushInterface;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/28.
 */

@PerActivity
public class RegisterPresenter extends YaRxPresenter<RegisterView> {

    private static final String TAG = RegisterPresenter.class.getSimpleName();
    private UserRepo userRepo;
    String username;

    private MyCount mc;
    @Inject
    public RegisterPresenter() {

    }

    // 点击验证码按钮时是否手机号格式正确、密码、确认密码是否正确，如果都正确才能发送验证码否则不能发送验证码并且给出提示信息
    public void check() {
        username = getView().getUsername();
        String password = getView().getPassword();
        String againPassword = getView().getAgainPassword();
        if (TextUtils.isEmpty(username)
                || TextUtils.isEmpty(againPassword)
                || TextUtils.isEmpty(password)) {
            getView().textNull(getView().context().getString(R.string.textNull));
        } else if (TextUtils.isEmpty(username) || !username.matches("[1][34578]\\d{9}")
                || TextUtils.isEmpty(password)
                || password.length() < 4 || password.length() > 20
                || !againPassword.equals(password)) {
            getView().checkIsTrue(getView().context().getString(R.string.check_error));
        } else {
            // 获取验证码
            getView().getVerfy();
        }

    }

    /**
     * 尝试注册
     */
    public void attemptRegister() {
        String s = "[1][34578]\\d{9}";
        username = getView().getUsername();

        if (TextUtils.isEmpty(username)) {
            getView().showUsernameError(getView().context().getString(R.string.error_username_no_null));
            return;
        }
//        else if(username.length()<4) {
//            registerView.showUsernameError(registerView.context().getString(R.string.error_username_short));
//            return;
//        }else if(username.length()>20) {
//            registerView.showUsernameError(registerView.context().getString(R.string.error_username_long));
//            return;
//        }
        else if (!username.matches("[1][34578]\\d{9}")) {
            getView().showUsernameError("格式不对");
            return;
        }
        final String password = getView().getPassword();
        if (TextUtils.isEmpty(password)) {
            getView().showPasswordError(getView().context().getString(R.string.error_password_no_null));
            return;
        } else if (password.length() < 4) {
            getView().showPasswordError(getView().context().getString(R.string.error_password_short));
            return;
        } else if (password.length() > 20) {
            getView().showPasswordError(getView().context().getString(R.string.error_password_long));
            return;
        }
        String againPassword = getView().getAgainPassword();
        if (TextUtils.isEmpty(againPassword)) {
            getView().showPasswordError(getView().context().getString(R.string.againpassword_no_null));
            return;
        } else if (!againPassword.equals(password)) {
            getView().showAgainPasswordError(getView().context().getString(R.string.error_password_defference));
            return;
        }
        String code = getView().getCode();
        if (TextUtils.isEmpty(code)) {
            getView().showAgainPasswordError(getView().context().getString(R.string.error_code_no_null));
            return;
        }
        String md5Password = MD5Util.getMD5String(password);
        // 尝试注册
        addUtilStop(userRepo.register(username, md5Password, code, Constants.APP_TOKEN)
                .flatMap(new Func1<TokenEntity, Observable<?>>() {
                    @Override
                    public Observable<?> call(TokenEntity tokenEntity) {
                        Log.e(TAG, "tokenEntity =" + tokenEntity + tokenEntity.getToken());
                        PreferencesUtils.putString(getView().context(), "Token", tokenEntity.getToken());
                        userRepo.saveUser(username, tokenEntity.getUser_nick(), password, tokenEntity.getToken());
                        return userRepo.rel_jpush(tokenEntity.getToken(), JPushInterface.getRegistrationID(getView().context()));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ProgressSubscriber<>(getView().context(), new SubscriberOnNextListener<BlankEntity>() {
                    @Override
                    public void onNext(BlankEntity blankEntity) {
                        PreferencesUtils.putString(getView().context(), "username", username);
                        if (JPushInterface.isPushStopped(getView().context())) {
                            JPushInterface.resumePush(getView().context());
                        }
                        getView().goToMain();
                    }
                }))
        );
    }

    public void codeStart() {
        mc = new MyCount(60000, 1000);
        mc.start();
    }

    public class MyCount extends CountDownTimer {
        /**
         * MyCount的构造方法
         *
         * @param millisInFuture    要倒计时的时间
         * @param countDownInterval 时间间隔
         */
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 在进行倒计时的时候执行的操作
            getView().codeTurn(millisUntilFinished);
        }

        @Override
        public void onFinish() {// 倒计时结束后要做的事情
            getView().codeStop();
        }

    }
}
