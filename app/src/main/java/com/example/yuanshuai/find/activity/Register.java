package com.example.yuanshuai.find.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.model.User;
import com.example.yuanshuai.find.model.UserInfoOutput;
import com.example.yuanshuai.find.net.Net;
import com.example.yuanshuai.find.tool.CheckPhone;
import com.example.yuanshuai.find.tool.CoutDownTimerUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Register extends AppCompatActivity {
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm)
    EditText confirm;
    @BindView(R.id.reg)
    Button reg;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.code)
    EditText code;



    private CoutDownTimerUtils coutDownTimerUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        init();
        initEvent();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private  void init(){
        coutDownTimerUtils=new CoutDownTimerUtils(getcode,60000,1000);
    }

    private boolean confirmNumber(){
        String num=number.getText().toString();
        return CheckPhone.isPhone(num);

    }
    private void initEvent(){
        RxView.clicks(getcode).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if(confirmNumber()){
                    Net.getNet().sendSms(number.getText().toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.immediate())
                            .subscribe(new Action1<Output>() {
                                @Override
                                public void call(Output output) {
                                    Log.e("register",""+output.getCode()+output.getCodeInfo()+output.getData());
                                    if(output.getCode()==0){
                                        coutDownTimerUtils.start();
                                    }
                                    else{
                                        showSnackBar("发送错误");
                                    }
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    showSnackBar(throwable.getMessage());
                                }
                            });
                }
                else
                {
                    number.setError("请输入正确手机号");
                }
            }


});
        RxView.clicks(reg).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if(validate()){
                    Net.getNet().register(password.getText().toString(),name.getText().toString(),number.getText().toString(),code.getText().toString())
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.immediate())
                            .subscribe(new Action1<Output<UserInfoOutput>>() {
                                @Override
                                public void call(Output output) {
                                    Log.e("register",""+output.getCode());
                                    if(output.getCode()==101)
                                        showSnackBar("用户名已存在");
                                    else if(output.getCode()==102)
                                        showSnackBar("验证码错误");
                                    else if(output.getCode()==201)
                                        showSnackBar("参数错误");
                                    else
                                    {
                                        Log.e("register",output.getData().toString());
                                        UserInfoOutput userInfoOutput=(UserInfoOutput)output.getData();
                                        Net.getNet().setUserInfoOutput(userInfoOutput);
                                        showSnackBar("正在自动登录");
                                        Intent intent=new Intent(Register.this,Main.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    showSnackBar(throwable.getMessage());
                                }
                            });
                }
            }
        });
    }
//验证密码
    private boolean validate(){
        if(password.getText().toString().length()<6){
            password.setError("密码长度应大于6");
            return false;
        }
        else if(!password.getText().toString().equals(confirm.getText().toString())){
            confirm.setError("两次密码不一致");
            return false;
        }
        else
            return true;
    }
}
