package com.example.yuanshuai.find.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.FindAadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindList extends AppCompatActivity {
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
        setContentView(R.layout.activity_find_list);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);
        init();
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
        findAadapter=new FindAadapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(findAadapter);
    }

}
