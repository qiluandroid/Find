package com.example.yuanshuai.find.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.tool.CheckPhone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Register extends AppCompatActivity {
    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm)
    EditText confirm;
    @BindView(R.id.reg)
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
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
    private void reg(){
        String number=user.getText().toString().trim();
        String pwd=password.getText().toString().trim();
        String pwdconfirm=confirm.getText().toString().trim();
        if(!CheckPhone.isPhone(number))
        {
            user.setError("请输入正确的手机号");
        }
        else if(!pwd.equals(pwdconfirm)) {
            confirm.setError("密码不一致");
        }
        else{
            register(number,pwd);
        }
    }
    private void register(String name,String password){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
