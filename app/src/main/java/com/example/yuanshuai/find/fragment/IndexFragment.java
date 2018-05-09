package com.example.yuanshuai.find.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.activity.Detail;
import com.example.yuanshuai.find.adapter.FindAadapter;
import com.example.yuanshuai.find.adapter.TypeAdapter;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;
import com.example.yuanshuai.find.tool.LocationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class IndexFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private SearchView searchView;
    private RecyclerView type;
    private RecyclerView find;
    private TypeAdapter typeAdapter;
    private FindAadapter findAadapter;
    private List<HashMap<String,Object>> types;
    private List<HashMap<String,Object>> finds;


    public IndexFragment() {
        // Required empty public constructor
    }

    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index,container,false);
        searchView= ButterKnife.findById(view,R.id.search);
        type=ButterKnife.findById(view,R.id.type);
        find=ButterKnife.findById(view,R.id.find);
        init();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void init(){
        types=new ArrayList<HashMap<String, Object>>();
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("image",R.mipmap.ren);
        hashMap.put("text","人");
        HashMap<String,Object> hashMap1=new HashMap<>();
        hashMap1.put("image",R.mipmap.chongwu);
        hashMap1.put("text","宠物");
        HashMap<String,Object> hashMap2=new HashMap<>();
        hashMap2.put("image",R.mipmap.wupin);
        hashMap2.put("text","物品");
        types.add(hashMap);
        types.add(hashMap1);
        types.add(hashMap2);
        typeAdapter=new TypeAdapter(getContext(),types);
        typeAdapter.setOnItemClickListener(new TypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                /*刷新数据*/
            }
        });
        type.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        type.setAdapter(typeAdapter);
        finds=new ArrayList<HashMap<String, Object>>();
        Location location=LocationUtils.getInstance(getActivity()).showLocation();
        Net.getNet().nearbyList(0,location.getLatitude(),location.getLongitude(),10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Output<List<Mission>>>() {
                        @Override
                        public void call(Output<List<Mission>> output) {
                        Log.e("db",""+output);
                        findAadapter.add(output.getData());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        HashMap<String,Object> map=new HashMap<>();
        map.put("image",R.mipmap.touxiang);
        map.put("text","测试");
        map.put("text2","2.0 km");
        map.put("text3","100￥");
        HashMap<String,Object> map1=new HashMap<>();
        map1.put("image",R.mipmap.touxiang);
        map1.put("text","狗子丢了");
        map1.put("text2","1.9 km");
        map1.put("text3","99 ￥");
        HashMap<String,Object> map2=new HashMap<>();
        map2.put("image",R.mipmap.touxiang);
        map2.put("text","狗子又丢了");
        map2.put("text2","3.0 km");
        map2.put("text3","99 ￥");
        HashMap<String,Object> map3=new HashMap<>();
        map3.put("image",R.mipmap.touxiang);
        map3.put("text","狗子丢了");
        map3.put("text2","4.0 km");
        map3.put("text3","99 ￥");
        finds.add(map1);
        finds.add(map);
        finds.add(map2);
        finds.add(map3);
        List<Mission> finds=new ArrayList<>();
        findAadapter=new FindAadapter(getContext(),finds);
        findAadapter.setOnItemClickListener(new TypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Net.getNet().setMission(findAadapter.getList().get(position));
                Intent intent=new Intent(getActivity(),Detail.class);
                startActivity(intent);
            }
        });
        find.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        find.setAdapter(findAadapter);







    }
    //    刷新列表
    public void flush(){
        Location location=LocationUtils.getInstance(getActivity()).showLocation();
        Net.getNet().nearbyList(0,location.getLatitude(),location.getLongitude(),100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Output<List<Mission>>>() {
                    @Override
                    public void call(Output<List<Mission>> listOutput) {
                        Log.e("f",""+listOutput.getData().size());
//                        放到recycleview上
                        findAadapter.add(listOutput.getData());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }


}
