package com.example.yuanshuai.find.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

public class MessageFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView messagelist;
    private MessageAdapter messageAdapter;
    private List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();

    public MessageFragment() {
    }

    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
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
        View view=inflater.inflate(R.layout.fragment_message, container, false);
        messagelist= ButterKnife.findById(view,R.id.messagelist);
        init();
        return view;
    }
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
    private void init(){
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("image",R.mipmap.cat);
        hashMap.put("text1","hahaha");
        hashMap.put("text2","hahaha");
        list.add(hashMap);
        list.add(hashMap);
        messageAdapter=new MessageAdapter(getContext(),list);
        messagelist.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        messagelist.setAdapter(messageAdapter);

    }
}
