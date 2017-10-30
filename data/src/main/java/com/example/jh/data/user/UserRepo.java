package com.example.jh.data.user;

import com.example.jh.data.PerActivity;
import com.example.jh.data.entity.BlankEntity;
import com.example.jh.data.entity.UserEntity;
import com.example.jh.data.net.HttpResultFunc;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

@PerActivity
public class UserRepo {

    private UserApi userApi;
    private UserDelegate userDelegate;

    @Inject
    UserRepo(UserApi userApi,UserDelegate userDelegate) {
        this.userApi = userApi;
        this.userDelegate = userDelegate;
    }

    /**
     * 以save,modify,delete,get等字眼作为方法开头
     */
    public void modifyLoginedNick(String nick) {
        userDelegate.updateUserNick(getUserLoginedUsername(),nick);
    }
    // 得到登录的用户名
    public String getUserLoginedUsername() {
        return getUserLogined().username();
    }
    UserEntity getUserLogined() {
        return userDelegate.selectUserByStatus(UserDelegate.LOGIN);
    }
    // 保存用户
    public void saveUser(String username, String nickname, String password, String token) {
        userDelegate.updateUserAllStatus(UserDelegate.UNLOGIN);
        if(userDelegate.selectUser(username)==null) {
            userDelegate.insertUser(username,nickname,password,token,UserDelegate.LOGIN);
        }else {
            userDelegate.updateUser(username,nickname,password,token,UserDelegate.LOGIN);
        }

    }




    public Observable<TokenEntity> login(String token, String username, String password) {
        return userApi.login(token,username, password)
                .map(new HttpResultFunc<>());
    }

    public Observable<BlankEntity> rel_jpush(String token, String jpushid) {
        return userApi.rel_jpush(token, jpushid)
                .map(new HttpResultFunc<>());
    }
}
