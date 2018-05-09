package com.example.yuanshuai.find.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.Message2Adapter;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Message extends AppCompatActivity {
    private Message2Adapter adapter;
    private AlertDialog alertDialog;
    @BindView(R.id.list)
    RecyclerView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        init();
        flush();
    }
    private void init(){
        List<com.example.yuanshuai.find.model.Message> list=new ArrayList<>();
        adapter=new Message2Adapter(list,Message.this);
        lv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        lv.setAdapter(adapter);
        adapter.setOnItemLongClickListener(new Message2Adapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position,String rid) {
                writeliuyan(rid);
            }
        });

    }
    private void flush(){
        Net.getNet().receiveList(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Output<List<com.example.yuanshuai.find.model.Message>>>() {
                    @Override
                    public void call(Output<List<com.example.yuanshuai.find.model.Message>> listOutput) {
                        Log.e("","");
                        adapter.add(listOutput.getData());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(Message.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void flush2(){
        Net.getNet().receiveList(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Output<List<com.example.yuanshuai.find.model.Message>>>() {
                    @Override
                    public void call(Output<List<com.example.yuanshuai.find.model.Message>> listOutput) {
                        Log.e("","");
                        adapter.cadd(listOutput.getData());

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(Message.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void writeliuyan(final String rid){
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
                        Toast.makeText(Message.this, "输入为空", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Net.getNet().messageReply(rid,input.getText().toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<Output>() {
                                    @Override
                                    public void call(Output output) {
                                        flush2();
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

