package com.example.jh.dianyou.features.login;

import android.text.TextUtils;


import com.example.jh.base.YaRxPresenter;
import com.example.jh.data.Constants;
import com.example.jh.data.PerActivity;
import com.example.jh.data.SubscriberOnNextListener;
import com.example.jh.data.entity.BlankEntity;
import com.example.jh.data.user.TokenEntity;
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
 * Created by flamingo on 2016/7/14.
 */

/**
 * // 每一个p层都必须要写这个构造方法才行
 *
 * @Inject LoginPresenter() {
 * }
 */
@PerActivity
public class LoginPresenter extends YaRxPresenter<LoginView> {

//    private UserRepo userRepo;

    //    @Inject
//    LoginPresenter(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
    @Inject
    LoginPresenter() {

    }

    public void attemptLogin() {
        getView().cleanError();

        final String username = getView().getUsername();
        if (TextUtils.isEmpty(username)) {
            getView().showUsernameError(getView().context().getString(R.string.error_username_no_null));
            return;
        } else if (!username.matches("[1][34578]\\d{9}")) {
            getView().showUsernameError(getView().context().getString(R.string.error_phone));
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
//        MD5加密
        String md5Password = MD5Util.getMD5String(password);
        // 尝试登录
//        addUtilStop(userRepo.login(Constants.APP_TOKEN,username,md5Password)
//                .flatMap(new Func1<TokenEntity, Observable<BlankEntity>>() {
//                    @Override
//                    public Observable<BlankEntity> call(TokenEntity tokenEntity) {
//                        PreferencesUtils.putString(getView().context(),"Token",tokenEntity.getToken());
//                        userRepo.saveUser(username,tokenEntity.getUser_nick(),password,tokenEntity.getToken());
//
//                        return userRepo.rel_jpush(tokenEntity.getToken(), JPushInterface.getRegistrationID(getView().context()));
//                    }
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ProgressSubscriber<BlankEntity>(getView().context(), new SubscriberOnNextListener<BlankEntity>() {
//
//                    @Override
//                    public void onNext(BlankEntity blankEntity) {
//                        getView().showResult(getView().context().getString(R.string.success_login));
//                        PreferencesUtils.putString(getView().context(),"username",username);
//                        if( JPushInterface.isPushStopped(getView().context())) {
//                            JPushInterface.resumePush(getView().context());
//                        }
//
                        getView().goToMain();
//
//                    }
//
//                }))
//        );
    }
}
