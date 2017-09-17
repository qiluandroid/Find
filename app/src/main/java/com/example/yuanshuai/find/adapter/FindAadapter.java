package com.example.yuanshuai.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuanshuai.find.R;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by yuanshuai on 2017/9/16.
 */

public class FindAadapter extends RecyclerView.Adapter<FindAadapter.MyViewHoloder> {
    private List<HashMap<String,Object>> list;
    private Context context;
    private TypeAdapter.OnItemClickListener mOnItemClickListener;
    private TypeAdapter.OnItemLongClickListener mOnItemLongClickListener;
    public FindAadapter(Context context,List<HashMap<String,Object>> list) {
        super();
        this.context=context;
        this.list=list;
    }

    @Override
    public void onBindViewHolder(MyViewHoloder holder, final int position) {
        holder.imageView.setImageResource((int)list.get(position).get("image"));
        holder.textView.setText(list.get(position).get("text").toString());
        holder.textView2.setText(list.get(position).get("text2").toString());
        holder.textView3.setText(list.get(position).get("text3").toString());
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
            textView3=ButterKnife.findById(itemView,R.id.money);
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
}
