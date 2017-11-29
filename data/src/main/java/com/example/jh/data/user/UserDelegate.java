package com.example.jh.data.user;

/**
 * Created by jinhui on 2017/11/29.
 * 用户接口类
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

    void updateUserAllStatus(int status);

//    UserEntity selectUser(String username);
}
