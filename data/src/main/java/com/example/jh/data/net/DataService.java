package com.example.jh.data.net;



import com.example.jh.data.alarmclock.AlarmConfigEntity;
import com.example.jh.data.article.ArticleEntity;
import com.example.jh.data.entity.BaseEntity;
import com.example.jh.data.entity.BlankEntity;
import com.example.jh.data.entity.ContactEntity;
import com.example.jh.data.entity.LaglngEntity;
import com.example.jh.data.entity.UsersEntity;
import com.example.jh.data.voice.VoiceEntity;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface DataService {


    @GET("tp/index.php/user/get_verify_code")
    Observable<BaseEntity<BlankEntity>> getverifycode(@Query("phone") String phone, @Query("token") String token);


    @POST("tp/index.php/bind/getbondedusers")
    Observable<BaseEntity<List<UsersEntity>>> getbondedusers(@Query("token") String token, @Query("imei") String imei);


    @POST("tp/index.php/bind/bindmaster")
    Observable<BaseEntity<BlankEntity>> bindmaster(@Query("token") String token, @Query("imei") String imei, @Query("user") String user);


    @Multipart
    @POST("tp/index.php/upload/upload")
    Call<ResponseBody> uploadFile(@Query("token") String token, @Part MultipartBody.Part file);


    @POST("tp/index.php/voice/PostVoice")
    Observable<BaseEntity<List<VoiceEntity>>> postvoice(@Query("token") String token, @Query("imei") String imei, @Query("url") String url);

//    @POST("tp/index.php/user/user_add_article")
//    Observable<BaseEntity<ArticleEntity>>user_add_article(@Query("token")String token,
//                                                          @Query("article_content")String article_content,
//                                                          @Query("article_title")String articlt_title,
//                                                          @Query("article_img_src")String article_img_src);
    @POST("tp/index.php/user/social_network_add")
    Observable<BaseEntity<ArticleEntity>> social_network_add(@Query("token") String token,
                                                             @Query("imei") String imei,
                                                             @Query("content") String content,
                                                             @Query("img") String img);



    @POST("tp/index.php/pb/GetDevicePB")
    Observable<BaseEntity<List<ContactEntity>>> getdevicepb(@Query("token") String token, @Query("imei") String imei);

    @POST("tp/index.php/pb/SetDevicePB")
    Observable<BaseEntity<List<Integer>>> setdevicepb(@Query("token") String token, @Query("imei") String imei, @Query("indexes") String indexes, @Query("numbers") String numbers, @Query("names") String names);

    @POST("tp/index.php/pb/addpb")
    Observable<BaseEntity<String>> addpb(@Query("token") String token, @Query("imei") String imei, @Query("number") String number, @Query("name") String name);


    @POST("tp/index.php/pb/editpb")
    Observable<BaseEntity<BlankEntity>> editpb(@Query("token") String token, @Query("imei") String imei, @Query("index") String index, @Query("number") String number, @Query("name") String name);

    @POST("tp/index.php/pb/delpb")
    Observable<BaseEntity<BlankEntity>> delpb(@Query("token") String token, @Query("imei") String imei, @Query("index") String index);

    // 删除相册
    @POST("tp/index.php/photo/delete_photo")
    Observable<BaseEntity<BlankEntity>> deleteAlbum(@Query("token") String token, @Query("imei") String imei, @Query("url") String url);

    // 添加闹钟
    @POST("tp/index.php/device/clockAdd")
    Observable<BaseEntity<String>> clockAdd(
            @Query("token") String token, @Query("imei") String imei,
            @Query("about") String about, @Query("begin") String begin,
            @Query("repeat") String repeat);

    @POST("tp/index.php/device/clockList")
    Observable<BaseEntity<List<AlarmConfigEntity>>> clockList(@Query("token") String token, @Query("imei") String imei);

    @POST("tp/index.php/device/clockDel")
    Observable<BaseEntity<String>> clockDel(
            @Query("token") String token, @Query("imei") String imei,
            @Query("index") String index);

    @POST("tp/index.php/device/clockEdit")
    Observable<BaseEntity<String>> clockEdit(
            @Query("token") String token, @Query("imei") String imei,
            @Query("index") String index, @Query("begin") String begin,
            @Query("about") String about, @Query("repeat") String repeat);
    @GET("tp/index.php/convert/convert")
    Observable<BaseEntity<LaglngEntity>> convert(@Query("from") String from, @Query("to") String to, @Query("lat") double lat, @Query("lng") double lng);

}
