package com.example.yuanshuai.find.tool;

import android.os.CountDownTimer;
import android.text.SpannableString;
import android.widget.TextView;

/**
 * Created by 12917 on 2017/3/14.
 */

public class CoutDownTimerUtils extends CountDownTimer {
    private TextView  textView;
    public CoutDownTimerUtils(TextView textView,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.textView=textView;
    }


    @Override
    public void onFinish() {
        textView.setText("重新获取验证码");
        textView.setClickable(true);
        //textView.setBackgroundResource(R.drawable.code_normal);

    }

    @Override
    public void onTick(long l) {
        textView.setClickable(false);
        textView.setText(l/1000+"秒后可重新发送");
        /*设置为不能点击*/
        //textView.setBackgroundResource(R.drawable.code_press);
        SpannableString spannableString=new SpannableString(textView.getText().toString());
        //ForegroundColorSpan span=new ForegroundColorSpan(Color.RED);
        //spannableString.setSpan(span,0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //textView.setText(spannableString);


    }
}
