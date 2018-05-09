package com.example.yuanshuai.find.net;


import com.example.yuanshuai.find.model.Message;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.model.Order;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.model.Response;
import com.example.yuanshuai.find.model.Root;
import com.example.yuanshuai.find.model.UserData;
import com.example.yuanshuai.find.model.UserInfoOutput;
import com.example.yuanshuai.find.model.request.SendSmsInput;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static com.example.yuanshuai.find.net.NetApi.apiorder;
import static com.example.yuanshuai.find.net.NetApi.mission;
import static com.example.yuanshuai.find.net.NetApi.pubaccount;


/**
 * Created by 12917 on 2017/7/18.
 */

public interface NetApi {

    String pubcommon="api/common/";
    String pubaccount="api/account/";
    String mission="api/mission/";
    String apiorder="api/order/";
    String apimessage="api/message/";

//    请求验证码
    @Headers({"Content-Type: text/html","Accept: application/json"})
    @POST(pubcommon+"sendSms")
    Observable<Output> sendSms(@Query("phone") String input);

//    注册
    @POST(pubaccount+"register")
    Observable<Output<UserInfoOutput>> register(@Query("password")String password, @Query("nickname")String nickname, @Query("phone")String phone, @Query("smsCode")String smsCode);

//   登陆
    @POST(pubaccount+"login")
    @FormUrlEncoded
    Observable<Output<UserInfoOutput>> login(@Field("username") String username,@Field("password") String password);
//    验证码登录
    @POST(pubaccount+"loginWithSmsCode")
    @FormUrlEncoded
    Observable<Output<UserInfoOutput>> loginWithSmsCode(@Field("phone")String phone,@Field("smsCode")String code);

//    修改密码
    @POST(pubaccount+"updatePassword")
    @FormUrlEncoded
    Observable<Output> updatePassword(@Field("password")String password);
//    获取头像
    @GET(pubaccount+"getUserAvatar")
    Observable<ResponseBody> getUserAvatar(@Query("id")String id);

//    上传头像
    @Multipart
    @POST(pubaccount+"setUserAvatar")
    Observable<Output> setAvatar(@Body RequestBody body);

//    修改昵称
    @POST(pubaccount+"update")
    @FormUrlEncoded
    Observable<Output> update(@Field("nickname")String name);

//    附近的任务列表
    @GET(mission+"nearbyList")
    Observable<Output<List<Mission>>> nearbyList(@Query("page")Integer page, @Query("x")Double x, @Query("y")Double y, @Query("range")Double range);

//    添加任务
    @POST(mission+"addMission")
    Observable<Output> addMission(@Body RequestBody body);
//    我发布的任务列表
    @GET(mission+"myList")
    Observable<Output<List<Mission>>> myList(@Query("page")Integer page);
    @GET(mission+"image")
    Observable<ResponseBody> image(@Query("id")String id);

//    结束发布的任务
    @POST(mission+"finish")
    @FormUrlEncoded
    Observable<Output> finish(@Field("missionId")String id);

//    评论任务
    @POST(mission+"addComment")
    @FormUrlEncoded
    Observable<Output> addComment(@Field("missionId")String id,@Field("comment")String comment);

//    回复评论
    @POST(mission+"addReplay")
    @FormUrlEncoded
    Observable<Output> addReplay(@Field("commentId")String id,@Field("reply")String reply);

//      打赏评论
    @POST(mission+"payComment")
    @FormUrlEncoded
    Observable<Output> payComment(@Field("commentId")String id,@Field("amount")int amount);

//    充值
    @POST(apiorder+"charge")
    @FormUrlEncoded
    Observable<ResponseBody> charge(@Field("amount")int amount,@Field("channel")String channer);

//    提现转账
    @POST(apiorder+"transfer")
    @FormUrlEncoded
    Observable<Output> transfer(@Field("amount")int amount,@Field("channel")String channel,@Field("recipient")String recipient,@Field("name")String name,@Field("cardNumber")String number,@Field("openBankCode")String code);

//    账单列表
    @GET(apiorder+"list")
    Observable<Output<List<Order>>> list(@Query("page")int page);

//    添加留言
    @POST(apimessage+"addMessage")
    @FormUrlEncoded
    Observable<Output> addMessage(@Field("toUserId")String userid,@Field("message")String message);

//    回复留言
    @POST(apimessage+"messageReply")
    @FormUrlEncoded
    Observable<Output> messageReply(@Field("messageId")String id,@Field("replay")String replay);

//    给别人的留言
    @GET(apimessage+"sendList")
    Observable<Output<List<Message>>> sendList(@Query("page")int page);

//    给我的留言
    @GET(apimessage+"receiveList")
    Observable<Output<List<Message>>> receiveList(@Query("page")int page);

    //   登陆
    @POST(pubaccount+"login")
    @FormUrlEncoded
    Observable<ResponseBody> login1(@Field("username") String username,@Field("password") String password);
//    测试
    @POST(pubaccount+"setUserAvatar")
    @Multipart
    Observable<ResponseBody> setAvatar2(@Part MultipartBody.Part imgs);





}
