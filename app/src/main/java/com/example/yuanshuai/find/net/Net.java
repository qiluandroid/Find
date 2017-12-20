package com.example.yuanshuai.find.net;


import android.util.Log;

import com.example.yuanshuai.find.model.Response;
import com.example.yuanshuai.find.model.Root;
import com.example.yuanshuai.find.model.UserData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by 12917 on 2017/7/15.
 */

public class Net {
    private static Net net;
    private String url="http://211.87.226.166:8080/saver/";
    private Retrofit retrofit;
    private OkHttpClient.Builder okHttpClient;
    private NetApi ret;
    private String username;
    public static synchronized Net getNet(){
        if(net==null)
            net=new Net();
        return net;
    }
    private Net(){
        okHttpClient=new OkHttpClient.Builder();
        okHttpClient.cookieJar(new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
                Log.e("a",""+cookies.get(0).toString());
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });


        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ret=retrofit.create(NetApi.class);
    }
    public Observable<Root> listCall(String user){
        return ret.listRoots(user);
    }

    /*登陆*/
    public Observable<Response> login(String name, String password){
        username=name;
        return ret.login(name,password);
    }
    /*登出*/
    public Observable<Response> logout(){
        return ret.logout();
    }
    /*请求验证码*/
    public Observable<Response> getcode(String number){
        return ret.getCode(number);
    }
    /*注册*/
    public Observable<Response> reg(String username,String password,String code){
        return ret.reg(username,password,"name",code);
    }
    /*获取积分情况*/
    public Observable<UserData> getData(){
        return ret.getData(username);
    }
    /*扫描二维码*/
    public Observable<ResponseBody> getqr(String id){
        return  ret.getqr(id,username);
    }
}
