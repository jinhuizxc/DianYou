package com.example.jh.data.user;



import com.example.jh.data.entity.BaseEntity;
import com.example.jh.data.entity.BlankEntity;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/2.
 */

public interface UserApi {
    @GET("tp/index.php/user/login")
    Observable<BaseEntity<TokenEntity>> login(@Query("token") String token, @Query("username") String username, @Query("password") String password);

    @GET("tp/index.php/user/register")
    Observable<BaseEntity<TokenEntity>> register(@Query("username") String username, @Query("password") String password, @Query("code") String code, @Query("token") String token);
    // 新增找回密码功能
    @GET("tp/index.php/user/resetPass")
    Observable<BaseEntity<BlankEntity>> resetpassword(@Query("token") String token, @Query("username") String username, @Query("password") String password, @Query("code") String code);

    @GET("tp/index.php/user/usernick")
    Observable<BaseEntity<BlankEntity>> usernick(@Query("token") String token, @Query("user_nick") String user_nick);
    @POST("tp/index.php/bind/rel_jpush")
    Observable<BaseEntity<BlankEntity>> rel_jpush(@Query("token") String token, @Query("jpushid") String jpushid);

}
