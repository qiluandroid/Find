package com.example.yuanshuai.find.net;


import com.example.yuanshuai.find.model.Response;
import com.example.yuanshuai.find.model.Root;
import com.example.yuanshuai.find.model.UserData;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 12917 on 2017/7/18.
 */

public interface NetApi {
    @GET("users/{user}/repos")
    Observable<Root> listRoots(@Path("user") String user);
    /*登陆*/
    @POST("app/login")
    @FormUrlEncoded
    Observable<Response> login(@Field("username") String name, @Field("password") String password);
    /*退出*/
    @GET
    Observable<Response> logout();
    /*请求发送验证码*/
    @GET("app/getcode")
    Observable<Response> getCode(@Query("number") String number);
    /*注册*/
    @GET("app/reg")
    Observable<Response> reg(@Query("username") String username, @Query("password") String password, @Query("name") String name, @Query("code") String code);
    /*获取积分情况*/
    @GET("app/getdata")
    Observable<UserData> getData(@Query("username") String name);
    /*扫描二维码*/
    @GET("app/putqr/{id}")
    Observable<ResponseBody> getqr(@Path("id") String id, @Query("name") String username);


}
