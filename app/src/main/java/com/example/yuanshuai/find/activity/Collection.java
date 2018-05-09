package com.example.yuanshuai.find.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.DatePicker;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.FindAadapter;
import com.example.yuanshuai.find.model.Mission;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Collection extends AppCompatActivity {

    @OnClick(R.id.back)
    public void back(){
        finish();
    }
    @BindView(R.id.list)
    RecyclerView recyclerView;

    private List<HashMap<String,Object>> list;
    private FindAadapter findAadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        init();
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().get().url("121.250.222.31:8080/data/user/getOrders").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }


    private void init(){
        list=new ArrayList<>();
        HashMap<String,Object> map=new HashMap<>();
        map.put("image",R.mipmap.dog);
        map.put("text","丢了");
        map.put("text2","好远");
        map.put("text3","不给钱");
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        List<Mission> list=new ArrayList<>();
        findAadapter=new FindAadapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(findAadapter);
    }

}
