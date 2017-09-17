package com.example.yuanshuai.find.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.activity.Detail;
import com.example.yuanshuai.find.adapter.FindAadapter;
import com.example.yuanshuai.find.adapter.TypeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

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
        hashMap.put("image",R.mipmap.dog);
        hashMap.put("text","狗");
        types.add(hashMap);
        types.add(hashMap);
        types.add(hashMap);
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
        HashMap<String,Object> map=new HashMap<>();
        map.put("image",R.mipmap.dog);
        map.put("text","丢了");
        map.put("text2","好远");
        map.put("text3","不给钱");
        finds.add(map);
        finds.add(map);finds.add(map);
        finds.add(map);
        finds.add(map);
        finds.add(map);
        findAadapter=new FindAadapter(getContext(),finds);
        findAadapter.setOnItemClickListener(new TypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),Detail.class);
                startActivity(intent);
            }
        });
        find.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        find.setAdapter(findAadapter);







    }


}
