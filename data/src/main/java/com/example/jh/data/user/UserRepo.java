package com.example.jh.data.user;

import com.example.jh.data.PerActivity;
import com.example.jh.data.entity.BlankEntity;
import com.example.jh.data.net.HttpResultFunc;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by jinhui on 2017/11/29.
 */

@PerActivity
public class UserRepo {

    private UserApi userApi;
    // 数据库相关的接口类
    private UserDelegate userDelegate;
    @Inject
    UserRepo(UserApi userApi,UserDelegate userDelegate) {
        this.userApi = userApi;
        this.userDelegate = userDelegate;

    }

    // 注册
    public Observable<TokenEntity> register(String username, String password, String code, String token) {
        return userApi.register(username, password, code, token)
                .map(new HttpResultFunc<>());
    }

    // 注册新用户到数据库
    public void saveUser(String username, String nickname, String password, String token) {
        userDelegate.updateUserAllStatus(UserDelegate.UNLOGIN);
//        if(userDelegate.selectUser(username)==null) {
//            userDelegate.insertUser(username,nickname,password,token,UserDelegate.LOGIN);
//        }else {
//            userDelegate.updateUser(username,nickname,password,token,UserDelegate.LOGIN);
//        }

    }

    // 注册极光？
    public Observable<BlankEntity> rel_jpush(String token, String jpushid) {
        return userApi.rel_jpush(token, jpushid)
                .map(new HttpResultFunc<>());
    }
}
