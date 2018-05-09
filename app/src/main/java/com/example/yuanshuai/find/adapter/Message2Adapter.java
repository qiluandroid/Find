package com.example.yuanshuai.find.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.ValueIterator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.model.Message;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.net.Net;
import com.example.yuanshuai.find.tool.GlideCircleTransform;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yuanshuai on 2018/4/16.
 */

public class Message2Adapter extends RecyclerView.Adapter<Message2Adapter.MyViewHolder> {
    private List<Message> list=new ArrayList<>();
    private Context context;
    private OnItemLongClickListener onItemLongClickListener;
    public Message2Adapter(List<Message> list,Context context) {
        super();
        this.list=list;
        this.context=context;
    }

    @Override
    public int getItemCount() {
//        return list.size();
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.message,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }
    public void add(List<Message> data){
        list.addAll(data);
        notifyDataSetChanged();
    }
    public void cadd(List<Message> data){
        list=data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Net.getNet().getAvatar(list.get(position).getFromUser().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
//                        holder.rhead.setBackground(new BitmapDrawable(BitmapFactory.decodeStream(body.byteStream())));
                        try {
                            Glide.with(context)
                                    .load(body.bytes())
                                    .into(holder.rhead);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("",""+throwable.getMessage());
                    }
                });

        holder.rname.setText(list.get(position).getFromUser().getNickname());
        holder.rtext.setText(list.get(position).getMessage());
        holder.rtime.setText(Net.getNet().getTime(list.get(position).getTime().toString()));
        if(list.get(position).getReplies().size()==0){
            holder.send.setVisibility(View.GONE);
            holder.stime.setVisibility(View.GONE);
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v,position,list.get(position).getId());
                    return false;
                }
            });
        }
        else
        {
            Glide.with(context)
                    .load(Net.getNet().getBs())
                    .into(holder.shead);
            holder.stext.setText(list.get(position).getReplies().get(0).getReply());
            holder.stime.setText(Net.getNet().getTime(list.get(position).getReplies().get(0).getTime()));
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView rhead;
        ImageView shead;
        TextView rtext;
        TextView stext;
        TextView rname;
        TextView sname;
        TextView rtime;
        TextView stime;
        LinearLayout send;
        LinearLayout recieve;
        public MyViewHolder(View itemView) {
            super(itemView);
            rhead=ButterKnife.findById(itemView,R.id.rhead);
            shead=ButterKnife.findById(itemView,R.id.shead);
            rtext=ButterKnife.findById(itemView,R.id.rtext);
            stext= ButterKnife.findById(itemView,R.id.stext);
            send=ButterKnife.findById(itemView,R.id.send);
            recieve=ButterKnife.findById(itemView,R.id.receive);
            rname=ButterKnife.findById(itemView,R.id.rname);
            sname=ButterKnife.findById(itemView,R.id.rtime);
            rtime=ButterKnife.findById(itemView,R.id.rtime);
            stime=ButterKnife.findById(itemView,R.id.stime);

        }

    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position,String rid);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
