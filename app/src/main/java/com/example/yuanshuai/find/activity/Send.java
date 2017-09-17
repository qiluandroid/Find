package com.example.yuanshuai.find.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Send extends AppCompatActivity {
    @OnClick(R.id.back)
    public void back(){
        finish();
    }
    @BindView(R.id.imagelist)
    RecyclerView imagelist;
    private ImageAdapter imageAdapter;
    private List<Uri> list;
    private final int SELECTREQUESTCODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);

        init();
    }
    private void init(){
        list=new ArrayList<>();
        Uri uri=null;
        list.add(uri);

        imageAdapter=new ImageAdapter(this,list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        imagelist.setLayoutManager(linearLayoutManager);
        imagelist.setAdapter(imageAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();
        list.add(list.size()-1,selectedImage);
        imageAdapter=new ImageAdapter(this,list);
        imagelist.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
        imagelist.setAdapter(imageAdapter);


    }
}
