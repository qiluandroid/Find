package com.example.yuanshuai.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuanshuai.find.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by yuanshuai on 2017/9/15.
 */

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHoler>  {
    private Context context;
    private List<HashMap<String,Object>> list=new ArrayList<HashMap<String, Object>>();
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    public TypeAdapter(Context context, List<HashMap<String,Object>> list) {
        super();
        this.context=context;
        this.list=list;
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, final int position) {
        holder.textView.setText(list.get(position).get("text").toString());
        holder.imageView.setImageResource((int)list.get(position).get("image"));
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,position);
                }
            });
        }
        if(mOnItemLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onItemLongClick(v,position);
                    return false;
                }
            });
        }
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.type_item,parent,false);
        return new MyViewHoler(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyViewHoler(View itemView) {
            super(itemView);
            imageView= ButterKnife.findById(itemView, R.id.typeimage);
            textView=ButterKnife.findById(itemView,R.id.typetext);
        }
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

}
