package com.example.yuanshuai.find.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.CommentAdapter;
import com.example.yuanshuai.find.adapter.DetailAdapter;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Detail extends AppCompatActivity {
    @OnClick(R.id.comment)
    public void comment(){
        writeComment();
    }
    @BindView(R.id.detaillist)
    RecyclerView recyclerView;
    @BindView(R.id.commentlist)
    RecyclerView commentlist;
    @BindView(R.id.detailHead)
    CircleImageView head;
    @BindView(R.id.detailid)
    TextView title;
    @BindView(R.id.detailmoney)
    TextView money;
    @BindView(R.id.detailcontent)
    TextView content;
    @OnClick(R.id.back)
    public void back(){
        finish();
    }
    @OnClick(R.id.liuyan)
    public void liuyan(){
        writeliuyan();
    }
    private AlertDialog alertDialog=null;
    List<String> list=new ArrayList<>();
    DetailAdapter detailAdapter;
    CommentAdapter commentAdapter;
    private Mission mission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        mission= Net.getNet().getMission();
//        加载头像
        Net.getNet().getAvatar(mission.getUser().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
                        try {
                            Glide.with(Detail.this).load(body.bytes())
                                    .into(head);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
//        加载标题
        title.setText(mission.getTitle());
//        加载价格
        money.setText(""+mission.getPrice());
//        加载描述
        content.setText(mission.getDescription());
//        图片
        list=mission.getImages();
        detailAdapter=new DetailAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(detailAdapter);
        commentAdapter=new CommentAdapter(this);
        commentlist.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        commentlist.setAdapter(commentAdapter);
        commentAdapter.add(mission.getComments());
        commentAdapter.setOnItemLongClickListener(new CommentAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position,String rid) {
                writeReply(rid);
            }
        });
    }
    private void writeliuyan(){
        if(alertDialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            View view= LayoutInflater.from(this).inflate(R.layout.ly,null,false);
            final EditText input=ButterKnife.findById(view,R.id.edit);
            Button cancel=ButterKnife.findById(view,R.id.cancel);
            Button ok=ButterKnife.findById(view,R.id.ok);
            builder.setView(view);
            alertDialog=builder.create();
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("".equals(input.getText().toString())){
                        Toast.makeText(Detail.this, "输入为空", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Net.getNet().addMessage(mission.getUser().getId(),input.getText().toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<Output>() {
                                    @Override
                                    public void call(Output output) {
                                        if (output.getCode() ==0) {
                                            Toast.makeText(Detail.this, "成功", Toast.LENGTH_SHORT).show();
                                            alertDialog.dismiss();
                                        }
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {

                                    }
                                });
                    }
                }
            });
        }
        alertDialog.show();
    }
    private void writeComment(){
        if(alertDialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            View view= LayoutInflater.from(this).inflate(R.layout.ly,null,false);
            final EditText input=ButterKnife.findById(view,R.id.edit);
            Button cancel=ButterKnife.findById(view,R.id.cancel);
            Button ok=ButterKnife.findById(view,R.id.ok);
            builder.setView(view);
            alertDialog=builder.create();
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("".equals(input.getText().toString())){
                        Toast.makeText(Detail.this, "输入为空", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Net.getNet().addComment(mission.getId(),input.getText().toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<Output>() {
                                    @Override
                                    public void call(Output output) {
                                        if(output.getCode()==0){
                                            Toast.makeText(Detail.this, "成功", Toast.LENGTH_SHORT).show();
                                            alertDialog.dismiss();
                                        }
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {

                                    }
                                });
                    }
                }
            });
        }
        alertDialog.show();
    }
    private void writeReply(final String rid){
        if(alertDialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            View view= LayoutInflater.from(this).inflate(R.layout.ly,null,false);
            final EditText input=ButterKnife.findById(view,R.id.edit);
            Button cancel=ButterKnife.findById(view,R.id.cancel);
            Button ok=ButterKnife.findById(view,R.id.ok);
            builder.setView(view);
            alertDialog=builder.create();
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if("".equals(input.getText().toString())){
                        Toast.makeText(Detail.this, "输入为空", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Net.getNet().addReplay(rid,input.getText().toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<Output>() {
                                    @Override
                                    public void call(Output output) {
                                        if(output.getCode()==0){
                                            Toast.makeText(Detail.this, "成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {

                                    }
                                });
                    }
                }
            });
        }
        alertDialog.show();
    }
}
