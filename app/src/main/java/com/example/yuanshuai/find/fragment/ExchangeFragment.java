package com.example.yuanshuai.find.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;
import com.pingplusplus.ui.CHANNELS;
import com.pingplusplus.ui.ChannelListener;
import com.pingplusplus.ui.PingppUI;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ExchangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExchangeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean[] bs=new boolean[]{false,false,false,false};
    LinearLayout m1;
    LinearLayout m2;
    LinearLayout m3;
    LinearLayout m4;
    int money=0;
    TextView money1;
    TextView money2;
    Button yes;
    Button no;

//
//    private OnFragmentInteractionListener mListener;

    public ExchangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExchangeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExchangeFragment newInstance(String param1, String param2) {
        ExchangeFragment fragment = new ExchangeFragment();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_exchange, container, false);
        money1= ButterKnife.findById(view,R.id.money);
        money2=ButterKnife.findById(view,R.id.monety2);
        yes=ButterKnife.findById(view,R.id.yes);
        no=ButterKnife.findById(view,R.id.cancel);
        m1=ButterKnife.findById(view,R.id.m1);
        m2=ButterKnife.findById(view,R.id.m2);
        m3=ButterKnife.findById(view,R.id.m3);
        m4=ButterKnife.findById(view,R.id.m4);
        init();
        flush();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//    刷新数据
    public void flush(){
        money1.setText(""+Net.getNet().getUserInfoOutput().getBalance());
        money2.setText(""+Net.getNet().getUserInfoOutput().getBalance());
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay();
            }
        });
    }
    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null !=msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null !=msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }
    public void pay(){
        for(int i=0;i<4;i++){
            if(bs[i]==true)
            {
                if(i==0)
                    money=5;
                else if(i==1)
                    money=10;
                else if(i==2)
                    money=20;
                else
                    money=40;
            }
        }
        CHANNELS[] channels = new CHANNELS[]{CHANNELS.ALIPAY, CHANNELS.WX, CHANNELS.UPACP,
                CHANNELS.BFB_WAP, CHANNELS.JDPAY_WAP, CHANNELS.QPAY,
                CHANNELS.CMB_WALLET, CHANNELS.YEEPAY_WAP};
        PingppUI.enableChannels(channels);

        // 参数一: context 上下文对象
        // 参数二: ChannelListener 选择渠道回调类
        PingppUI.showPaymentChannels(getActivity(), new ChannelListener() {
            @Override public void selectChannel(String channel) {
                // channel 为用户选择的支付渠道
                Net.getNet().charge(money,channel)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<ResponseBody>() {
                            @Override
                            public void call(ResponseBody output) {
                                try {
                                    m1.setBackgroundResource(R.drawable.back3);
                                    m2.setBackgroundResource(R.drawable.back3);
                                    m3.setBackgroundResource(R.drawable.back3);
                                    m4.setBackgroundResource(R.drawable.back3);
                                    Log.e("charge", "" + output.string().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("charge",""+throwable.getMessage());
                            }
                        });
            }
        });
    }
    public void init(){
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bs[0]=true;
                bs[1]=false;
                bs[2]=false;
                bs[3]=false;
                m1.setBackgroundResource(R.drawable.back_select);
                m2.setBackgroundResource(R.drawable.back3);
                m3.setBackgroundResource(R.drawable.back3);
                m4.setBackgroundResource(R.drawable.back3);
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bs[1]=true;
                bs[0]=false;
                bs[2]=false;
                bs[3]=false;
                m2.setBackgroundResource(R.drawable.back_select);
                m1.setBackgroundResource(R.drawable.back3);
                m3.setBackgroundResource(R.drawable.back3);
                m4.setBackgroundResource(R.drawable.back3);
            }
        });
        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bs[2]=true;
                bs[0]=false;
                bs[1]=false;
                bs[3]=false;
                m3.setBackgroundResource(R.drawable.back_select);
                m1.setBackgroundResource(R.drawable.back3);
                m2.setBackgroundResource(R.drawable.back3);
                m4.setBackgroundResource(R.drawable.back3);
            }
        });
        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bs[3]=true;
                bs[0]=false;
                bs[1]=false;
                bs[2]=false;
                m4.setBackgroundResource(R.drawable.back_select);
                m1.setBackgroundResource(R.drawable.back3);
                m2.setBackgroundResource(R.drawable.back3);
                m3.setBackgroundResource(R.drawable.back3);
            }
        });
    }
}
