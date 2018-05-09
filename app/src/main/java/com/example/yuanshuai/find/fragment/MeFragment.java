package com.example.yuanshuai.find.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.activity.Collection;
import com.example.yuanshuai.find.activity.FindList;
import com.example.yuanshuai.find.activity.Income;
import com.example.yuanshuai.find.activity.MyZan;
import com.example.yuanshuai.find.activity.Pay;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;
import com.example.yuanshuai.find.tool.GlideCircleTransform;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int REQUEST_CODE_LOCAL=1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ImageView circleImageView;
    private TextView textView;


   // private OnFragmentInteractionListener mListener;

    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
        View view=inflater.inflate(R.layout.fragment_me, container, false);
        LinearLayout resetPassword=ButterKnife.findById(view,R.id.resetPassword);
        LinearLayout linearLayout= ButterKnife.findById(view,R.id.hasfabu);
//        LinearLayout linearLayout1=ButterKnife.findById(view,R.id.collection);
        LinearLayout linearLayout2=ButterKnife.findById(view,R.id.pay);
        LinearLayout linearLayout3=ButterKnife.findById(view,R.id.shouru);
//        LinearLayout linearLayout4=ButterKnife.findById(view,R.id.zan);
        LinearLayout linearLayout5=ButterKnife.findById(view,R.id.help);
        circleImageView=ButterKnife.findById(view,R.id.headImage);
        textView=ButterKnife.findById(view,R.id.name);
        if(Net.getNet().getHeadImage()!=null) {
//            circleImageView.setBackground(Net.getNet().getHeadImage());
            Glide.with(getContext())
                    .load(Net.getNet().getBs())
                    .transform(new GlideCircleTransform(getContext()))
                    .into(circleImageView);

            textView.setText(Net.getNet().getUserInfoOutput().getNickname());
        }
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(),"未授予读写权限",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");

                } else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }
                getActivity().startActivityForResult(intent, REQUEST_CODE_LOCAL);


            }
        });
//        修改昵称
        RxView.clicks(textView).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.nickname,null);
                final EditText nick=ButterKnife.findById(view,R.id.nick);
                Button yes=ButterKnife.findById(view,R.id.yes);
                Button cancel=ButterKnife.findById(view,R.id.cancel);
                builder.setView(view);
                final AlertDialog alertDialog=builder.create();
                RxView.clicks(yes).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Log.e("db","yes");
                        if(!("").equals(nick.getText().toString())){
                            Net.getNet().update(nick.getText().toString())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<Output>() {
                                        @Override
                                        public void call(Output output) {
                                            Log.e("db", "o" + output.getCode());
                                            if (output.getCode() == 0) {
                                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                                                Net.getNet().getUserInfoOutput().setNickname(nick.getText().toString());
                                                flush();
                                                alertDialog.dismiss();
                                            }
                                        }
                                    }, new Action1<Throwable>() {
                                        @Override
                                        public void call(Throwable throwable) {
                                            Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
                RxView.clicks(cancel).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }

        });

        RxView.clicks(linearLayout).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Intent intent=new Intent(getActivity(), FindList.class);
                startActivity(intent);
            }
        });
//        RxView.clicks(linearLayout1).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Intent intent=new Intent(getActivity(), Collection.class);
//                startActivity(intent);
//            }
//        });
        RxView.clicks(linearLayout2).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Intent intent=new Intent(getActivity(), Pay.class);
                startActivity(intent);
            }
        });
        RxView.clicks(linearLayout3).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Intent intent=new Intent(getActivity(), Income.class);
                startActivity(intent);
            }
        });
//        RxView.clicks(linearLayout4).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Intent intent=new Intent(getActivity(), MyZan.class);
//                startActivity(intent);
//            }
//        });
        RxView.clicks(resetPassword).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.password,null);
                final EditText pass=ButterKnife.findById(view,R.id.pass);
                Button yes=ButterKnife.findById(view,R.id.yes);
                Button cancel=ButterKnife.findById(view,R.id.cancel);
                builder.setView(view);
                final AlertDialog alertDialog=builder.create();
                RxView.clicks(yes).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Log.e("db","yes");
                        if((!("").equals(pass.getText().toString()))&&pass.getText().toString().length()>=6){
                            Net.getNet().updatePassword(pass.getText().toString())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<Output>() {
                                        @Override
                                        public void call(Output output) {
                                            Log.e("db", "o" + output.getCode());
                                            if (output.getCode() == 0) {
                                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                            }
                                            Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                                        }
                                    }, new Action1<Throwable>() {
                                        @Override
                                        public void call(Throwable throwable) {
                                            Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
                RxView.clicks(cancel).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        return view;
    }

//    刷新个人信息
    public void flush(){
        Log.e("db","flush");
        if(Net.getNet().getHeadImage()!=null) {
//            circleImageView.setBackground(Net.getNet().getHeadImage());
            Glide.with(getContext())
                    .load(Net.getNet().getBs())
                    .transform(new GlideCircleTransform(getContext()))
                    .into(circleImageView);

            textView.setText(Net.getNet().getUserInfoOutput().getNickname());
        }
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


}
