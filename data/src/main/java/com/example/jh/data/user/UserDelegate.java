package com.example.jh.data.user;

import com.example.jh.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

public interface UserDelegate {

    /**
     * 以insert,update,delete,select等字眼作为方法开头
     * 紧接的是实体名字
     * update的时候再跟上要更新的字段 如{@link UserDelegate#updateUserNick(String, String)}
     * 查询的时候加上By和查询的字段 如 {@link UserDelegate#selectUserByStatus(int)}
     * 对全体字段进行操作加上All 如 {@link UserDelegate#updateUserAllStatus(int)}
     **/
    int NEW = 0;
    int LOGIN = 1;
    int UNLOGIN = 2;

    void insertUser(String username,String nickname,String password,String token,int status);
    void updateUserNick(String username,String nick);

    UserEntity selectUserByStatus(int login);

    void updateUserAllStatus(int status);

    UserEntity selectUser(String username);

    void updateUser(String username,String nickname,String password,String token,int status);
    // 这个方法暂时注释掉 2017-11-3
//    Observable<List<UserEntity>> selectRxUserByStatus(int status);

    void deleteUser(String username);
    void updateUserPassword(String username,String password);
    void updateUserToken(String username,String token);
    void updateUserStatus(String username,int status);

}
