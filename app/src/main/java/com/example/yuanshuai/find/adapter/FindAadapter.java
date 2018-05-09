package com.example.yuanshuai.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;
import com.example.yuanshuai.find.net.NetApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yuanshuai on 2017/9/16.
 */

public class FindAadapter extends RecyclerView.Adapter<FindAadapter.MyViewHoloder> {
    private List<Mission> list;
    private Context context;
    private TypeAdapter.OnItemClickListener mOnItemClickListener;
    private TypeAdapter.OnItemLongClickListener mOnItemLongClickListener;
    public FindAadapter(Context context,List<Mission> list) {
        super();
        this.context=context;
        this.list=list;
    }

    @Override
    public void onBindViewHolder(final MyViewHoloder holder, final int position) {
        if(list.get(position).getImages().size()>0) {
//            Log.e("imageid",""+Net.getNet().getUrl()+NetApi.mission+"image?id="+list.get(position).getImages().get(0));
            Net.getNet().image(list.get(position).getImages().get(0).toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResponseBody>() {
                        @Override
                        public void call(ResponseBody output) {
                            try {
                                Glide.with(context).load(output.bytes())
                                        .into(holder.imageView);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

        }

        holder.textView.setText(list.get(position).getTitle());
        holder.textView2.setText(list.get(position).getAddress());
        holder.textView3.setText(""+list.get(position).getPrice());
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,position);
                }
            });
        }
        if(mOnItemLongClickListener!=null){
            holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onItemClick(v,position);
                    return false;
                }
            });
        }
    }
    public void add(List<Mission> col){
        list.addAll(col);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHoloder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.finditem,parent,false);
        return new MyViewHoloder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHoloder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;
        public MyViewHoloder(View itemView) {
            super(itemView);
            imageView= ButterKnife.findById(itemView,R.id.findImage);
            textView=ButterKnife.findById(itemView,R.id.findtitle);
            textView2=ButterKnife.findById(itemView,R.id.finddistance);
            textView3=ButterKnife.findById(itemView,R.id.moneyy);
        }
    }
    public void setOnItemClickListener(TypeAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(TypeAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    public List<Mission> getList() {
        return list;
    }
}
