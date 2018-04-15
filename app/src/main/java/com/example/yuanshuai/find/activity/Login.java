package com.example.yuanshuai.find.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.model.User;
import com.example.yuanshuai.find.model.UserInfoOutput;
import com.example.yuanshuai.find.net.Net;
import com.jakewharton.rxbinding.view.RxView;

import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Login extends AppCompatActivity {
    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.password)
    EditText pwd;
    @BindView(R.id.remeber)
    CheckBox remeber;
    @BindView(R.id.reg)
    TextView reg;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.phoneLogin)
    TextView phoneLogin;

    @OnClick(R.id.phoneLogin)
    public void setPhoneLogin(){
        /*手机验证码登陆*/
        Intent intent=new Intent(Login.this,PhoneLogin.class);
        startActivity(intent);
    }
    @OnClick(R.id.reg)
    public void reg(){
        Intent intent=new Intent();
        intent.setClass(Login.this,Register.class);
        startActivity(intent);
    }
    @OnClick(R.id.login)
    public void login(){
        checkandlogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
    }
    private void init(){

    }
    private void checkandlogin(){
        String username=user.getText().toString();
        String password=pwd.getText().toString();
        if(username.equals(""))
        {
            user.setError("用户名不能为空");
        }
        else if(password.equals("")){
            pwd.setError("密码不能为空");
        }
        else{
            login(username,password);
        }
    }
    private void login(final String username, String password){
        Net.getNet().login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<Output<UserInfoOutput>>() {
                               @Override
                               public void call(Output output) {
                                   if(output.getCode()==100)
                                       showSnackBar("登录失败");
                                   else if(output.getCode()==201)
                                       showSnackBar("参数错误");
                                   else
                                   {


                                       UserInfoOutput userInfoOutput=(UserInfoOutput)output.getData();
                                       Log.e("login",userInfoOutput.toString());
                                       Net.getNet().setUserInfoOutput(userInfoOutput);
                                       Intent intent=new Intent(Login.this,Splash.class);
                                       startActivity(intent);
                                       finish();
                                   }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                showSnackBar(throwable.getMessage());
                            }
                        });
//        Net.getNet().login1(username,password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.immediate())
//                .subscribe(new Action1<ResponseBody>() {
//                               @Override
//                               public void call(ResponseBody output) {
//                                   try {
//                                       Log.e("login",""+output.string());
//                                   } catch (IOException e) {
//                                       e.printStackTrace();
//                                   }
//                               }
//                           },
//                        new Action1<Throwable>() {
//                            @Override
//                            public void call(Throwable throwable) {
//                                showSnackBar(throwable.getMessage());
//                            }
//                        });

    }
    public void showSnackBar(String message){
        final Snackbar snackbar=Snackbar.make(getWindow().getDecorView(),message,Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("知道了",new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
