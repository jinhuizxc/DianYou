package com.example.jh.data.user;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.jh.data.entity.UserEntity;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jinhui on 2017/10/30.
 * Email：1004260403@qq.com
 */

public class UserDelegateImpl implements UserDelegate {

    private BriteDatabase mBriteDb;

    @Inject
    UserDelegateImpl(BriteDatabase mBriteDb) {
        this.mBriteDb = mBriteDb;
    }
    @Override
    public void insertUser(String username, String nickname, String password, String token, int status) {
        UserEntity user = UserEntity.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .status(status)
                .create_at(System.currentTimeMillis() / 1000)
                .build();
        mBriteDb.insert(UserEntity.TABLE_NAME, UserEntity.FACTORY.marshal(user).asContentValues());
    }

    @Override
    public void updateUserNick(String username, String nickname) {
        ContentValues values = new ContentValues();
        values.put("nickname", nickname);
        mBriteDb.update(UserEntity.TABLE_NAME, values, "username=?", username);
    }

    @Override
    public UserEntity selectUserByStatus(int status) {
        Cursor cursor = mBriteDb.query("select * from User where status=?", status+"");
        if(cursor.moveToNext()) {
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String token = cursor.getString(cursor.getColumnIndex("token"));
            int create_at = cursor.getInt(cursor.getColumnIndex("create_at"));
            return UserEntity.builder()
                    .username(username)
                    .nickname(nickname)
                    .password(password)
                    .token(token)
                    .status(status)
                    .create_at(create_at)
                    .build();
        }
        return null;
    }

    @Override
    public void updateUserAllStatus(int status) {
        ContentValues values = new ContentValues();
        values.put("status", status);
        mBriteDb.update(UserEntity.TABLE_NAME,values,"");
    }

    @Override
    public UserEntity selectUser(String username) {
        Cursor cursor = mBriteDb.query("select * from User where username=?", username);
        if(cursor.moveToNext()) {
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            String token = cursor.getString(cursor.getColumnIndex("token"));
            int status = cursor.getInt(cursor.getColumnIndex("status"));
            long create_at = cursor.getLong(cursor.getColumnIndex("create_at"));
            return UserEntity.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .token(token)
                    .status(status)
                    .create_at(create_at)
                    .build();
        }
        return null;
    }

    @Override
    public void updateUser(String username, String nickname, String password, String token, int status) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("nickname", nickname);
        values.put("token", token);
        values.put("status", status);
        mBriteDb.update(UserEntity.TABLE_NAME, values, "username=?", username);
    }

    /**
     * // 这个方法暂时注释掉 2017-11-3
     * @param username
     */
//    @Override
//    public Observable<List<UserEntity>> selectRxUserByStatus(int status) {
//        QueryObservable cursor = mBriteDb.createQuery(UserEntity.TABLE_NAME,UserEntity.GET_ALL_BY_STATUS,status+"");
//        Observable<List<UserEntity>> listObservable = cursor.mapToList(new Func1<Cursor, UserEntity>() {
//            @Override
//            public UserEntity call(Cursor cursor) {
//                return UserEntity.MAPPER.map(cursor);
//            }
//        });
//        return listObservable;
//    }

    @Override
    public void deleteUser(String username) {
        mBriteDb.delete(UserEntity.TABLE_NAME, null);
    }

    @Override
    public void updateUserPassword(String username, String password) {
        ContentValues values = new ContentValues();
        values.put("password", password);
        mBriteDb.update(UserEntity.TABLE_NAME, values, "username=?", username);
    }

    @Override
    public void updateUserToken(String username, String token) {
        ContentValues values = new ContentValues();
        values.put("token", token);
        mBriteDb.update(UserEntity.TABLE_NAME, values, "username=?", username);
    }

    @Override
    public void updateUserStatus(String username, int status) {
        ContentValues values = new ContentValues();
        values.put("status", status);
        mBriteDb.update(UserEntity.TABLE_NAME, values, "username=?", username);
    }
}
