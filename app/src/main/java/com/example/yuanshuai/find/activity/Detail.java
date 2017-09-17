package com.example.yuanshuai.find.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Detail extends AppCompatActivity {
    @BindView(R.id.detaillist)
    RecyclerView recyclerView;
    @OnClick(R.id.liuyan)
    public void liuyan(){
        writeliuyan();
    }
    private AlertDialog alertDialog=null;
    List<String> list=new ArrayList<>();
    DetailAdapter detailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        list.add(null);
        list.add(null);
        list.add(null);
        detailAdapter=new DetailAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(detailAdapter);
    }
    private void writeliuyan(){
        if(alertDialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            View view= LayoutInflater.from(this).inflate(R.layout.ly,null,false);
            builder.setView(view);
            alertDialog=builder.create();
        }
        alertDialog.show();
    }
}
