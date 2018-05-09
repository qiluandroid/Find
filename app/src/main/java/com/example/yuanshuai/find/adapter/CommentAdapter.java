package com.example.yuanshuai.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.model.MissionComment;
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
 * Created by yuanshuai on 2018/5/4.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    private Context context;
    private List<MissionComment> list=new ArrayList<>();
    private OnItemLongClickListener onItemLongClickListener;
    public CommentAdapter(Context context) {
        super();
        this.context=context;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.cname.setText(list.get(position).getUser().getNickname());
        holder.ctime.setText(Net.getNet().getTime(list.get(position).getTime()));
        holder.content.setText(list.get(position).getComment());
        Net.getNet().getAvatar(list.get(position).getUser().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
                        try {
                            Glide.with(context)
                                    .load(body.bytes())
                                    .into(holder.chead);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        if(list.get(position).getReplies().size()==0){
            holder.reply.setVisibility(View.GONE);
            holder.comment.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v,position,list.get(position).getId());
                    return false;
                }
            });
        }
        else{
            holder.rname.setText(list.get(position).getReplies().get(0).getUser().getNickname()+":");
            holder.rcontent.setText(list.get(position).getReplies().get(0).getReply());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void add(List<MissionComment> data){
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.comment,parent,false);

        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout comment;
        ImageView chead;
        TextView ctime;
        TextView cname;
        TextView content;
        LinearLayout reply;
        TextView rname;
        TextView rcontent;
        public MyViewHolder(View itemView) {
            super(itemView);
            comment=ButterKnife.findById(itemView,R.id.comment);
            chead= ButterKnife.findById(itemView, R.id.chead);
            ctime=ButterKnife.findById(itemView,R.id.ctime);
            cname=ButterKnife.findById(itemView,R.id.cname);
            content=ButterKnife.findById(itemView,R.id.content);
            reply=ButterKnife.findById(itemView,R.id.reply);
            rname=ButterKnife.findById(itemView,R.id.rname);
            rcontent=ButterKnife.findById(itemView,R.id.rcontent);
        }
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position,String rid);
    }
}
