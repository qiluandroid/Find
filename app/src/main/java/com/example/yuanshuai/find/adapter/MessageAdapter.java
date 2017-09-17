package com.example.yuanshuai.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by yuanshuai on 2017/9/15.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private Context context;
    private List<HashMap<String,Object>> list;
    public MessageAdapter(Context context,List<HashMap<String,Object>> list) {
        super();
        this.list=list;
        this.context=context;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView.setImageResource((int)list.get(position).get("image"));
        holder.textView.setText(list.get(position).get("text1").toString());
        holder.textView2.setText(list.get(position).get("text2").toString());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.messageitem,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView2;
        TextView textView3;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= ButterKnife.findById(itemView,R.id.messagehead);
            textView=ButterKnife.findById(itemView,R.id.messagetitle);
            textView2=ButterKnife.findById(itemView,R.id.messagecontent);
            textView3=ButterKnife.findById(itemView,R.id.messageis);
        }
    }
}
