package com.example.yuanshuai.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.net.Net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yuanshuai on 2017/9/17.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {
    List<String> list=new ArrayList<>();
    private Context context;
    public DetailAdapter(Context context,List<String> list) {
        super();
        this.list=list;
        this.context=context;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Net.getNet().image(list.get(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
                        try {
                            Glide.with(context).load(body.bytes())
                                    .into(holder.imageView);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        holder.imageView.setImageResource(R.mipmap.splash);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.detailitem,parent,false));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= ButterKnife.findById(itemView, R.id.image);
        }
    }
}
