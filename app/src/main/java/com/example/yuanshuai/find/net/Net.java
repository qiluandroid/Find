package com.example.yuanshuai.find.net;


import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import com.example.yuanshuai.find.activity.Send;
import com.example.yuanshuai.find.model.Message;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.model.Order;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.model.Response;
import com.example.yuanshuai.find.model.Root;
import com.example.yuanshuai.find.model.UserData;
import com.example.yuanshuai.find.model.UserInfoOutput;
import com.example.yuanshuai.find.model.request.SendSmsInput;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
//    用户属性
    private UserInfoOutput userInfoOutput;
    private Drawable headImage=null;
    private String name;
    private byte[] bs=null;
    private static Net net;
    private String url="http://suqingfa.win:8080/";
    private Retrofit retrofit;
    private OkHttpClient.Builder okHttpClient;
    private NetApi ret;
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

//    请求验证码
    public Observable<Output> sendSms(String phone){
        return ret.sendSms(phone);
    }
//    注册
    public Observable<Output<UserInfoOutput>> register(String password,String nickname,String phone,String smsCode){
        return ret.register(password,nickname,phone,smsCode);
    }
//    登陆
    public Observable<Output<UserInfoOutput>> login(String name,String password){
        return  ret.login(name,password);
    }
//    获取头像
    public Observable<ResponseBody> getUserAvatar(){
        return ret.getUserAvatar(userInfoOutput.getId());
    }
//    设置头像
    public Observable<Output> setUserAvatar(String path){
        File file=new File(path);
        RequestBody requestBody=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/*"), file)).build();
        return  ret.setAvatar(requestBody);
    }
//    测试
    public Observable<ResponseBody> c(String path){
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        return  ret.setAvatar2(body);
    }
//    修改昵称
    public Observable<Output> update(String name){
        return ret.update(name);
    }
//    附近任务列表
    public Observable<Output<List<Mission>>> nearbyList(Integer page, double x, double y, double range){
        return ret.nearbyList(page,x,y,range);
    }
//    发布任务
    public Observable<Output> addMission(Double x,Double y,String address,String title,String description,int price,List<String> paths){
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("x",""+x)
                .addFormDataPart("y",""+y)
                .addFormDataPart("address",address)
                .addFormDataPart("title",title)
                .addFormDataPart("description",description)
                .addFormDataPart("price",""+price);
        for(int i=0;i<paths.size()-1;i++){
            File file=new File(paths.get(i));
            Log.e("path",paths.get(i));
            builder.addFormDataPart("files",file.getName(),RequestBody.create(MediaType.parse("image/*"),file));
        }
        RequestBody requestBody=builder.build();
        return ret.addMission(requestBody);
    }
//    我发布的任务
    public Observable<Output<List<Mission>>> myList(Integer page){
        return ret.myList(page);
    }
//    结束发布的任务
    public Observable<Output> finish(String id){
        return ret.finish(id);
    }
//    评论任务
    public Observable<Output> addComment(String id,String comment){
        return ret.addComment(id,comment);
    }
//    回复评论
    public Observable<Output> addReplay(String id,String reply){
        return ret.addReplay(id,reply);
    }
//    打赏评论
    public Observable<Output> payComment(String id,int amount){
        return ret.payComment(id,amount);
    }
//    充值
    public Observable<Output> charge(int amount,String channer){
        return ret.charge(amount,channer);
    }
//    提现转账
    public Observable<Output> transfer(int amount,String channel,String recipient,String name,String number,String code){
        return ret.transfer(amount,channel,recipient,name,number,code);
    }
//    账单列表
    public Observable<Output<List<Order>>> list(int page){
        return ret.list(page);
    }
//    添加留言
    public Observable<Output> addMessage(String userid,String message){
        return ret.addMessage(userid,message);
    }
//    回复留言
    public Observable<Output> messageReply(String id,String replay){
        return ret.messageReply(id,replay);
    }
//    给别人的留言
    public Observable<Output<List<Message>>> sendList(int page){
        return ret.sendList(page);
    }
//    给我的留言
    public Observable<Output<List<Message>>> receiveList(int page){
        return ret.receiveList(page);
    }



    public byte[] getBs() {
        return bs;
    }

    public void setUserInfoOutput(UserInfoOutput userInfoOutput) {
        this.userInfoOutput = userInfoOutput;
    }



    public UserInfoOutput getUserInfoOutput() {
        return userInfoOutput;
    }








//    设置头像


    public void setHeadImage(Drawable headImage) {
        this.headImage = headImage;
    }

    public Drawable getHeadImage() {
        return headImage;
    }

    public void setBs(byte[] bs) {
        this.bs = bs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
