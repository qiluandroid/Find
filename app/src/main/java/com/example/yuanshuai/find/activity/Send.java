package com.example.yuanshuai.find.activity;

import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.ImageAdapter;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;
import com.example.yuanshuai.find.tool.LocationUtils;
import com.jakewharton.rxbinding.view.RxView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class Send extends AppCompatActivity {
    @OnClick(R.id.back)
    public void back(){
        finish();
    }
    @BindView(R.id.imagelist)
    RecyclerView imagelist;
    @BindView(R.id.sendtitle)
    EditText title;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.money)
    EditText mondey;
    @BindView(R.id.send)
    Button send;
    private ImageAdapter imageAdapter;
    private List<Uri> list;
    private List<String> paths;
    private final int SELECTREQUESTCODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);

        init();
    }
    private void init(){
        list=new ArrayList<>();
        paths=new ArrayList<>();
        Uri uri=null;
        list.add(uri);
        paths.add("");
        imageAdapter=new ImageAdapter(this,list,paths);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        imagelist.setLayoutManager(linearLayoutManager);
        imagelist.setAdapter(imageAdapter);
        RxView.clicks(send).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if(check()){
                    Location location=LocationUtils.getInstance(Send.this).showLocation();
                    Net.getNet().addMission(location.getLatitude(),location.getLongitude(),address.getText().toString(),title.getText().toString(),content.getText().toString(),1,imageAdapter.getPaths())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<Output>() {
                                @Override
                                public void call(Output output) {
                                    if(output.getCode()==0){
                                        showSnackBar("成功");
                                    }
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    showSnackBar(throwable.getMessage());
                                }
                            });
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImage = data.getData();
        list.add(list.size()-1,selectedImage);
        paths.add(paths.size()-1,getPath(selectedImage));
        imageAdapter=new ImageAdapter(this,list,paths);
        imagelist.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
        imagelist.setAdapter(imageAdapter);


    }
    protected String  getPath(Uri selectedImage) {
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;

            if (picturePath == null || picturePath.equals("null")) {

                return null;
            }
            return picturePath;
        } else {
            File file = new File(selectedImage.getPath());
            if (!file.exists()) {

                return null;

            }
            return  file.getAbsolutePath();
        }

    }
//    检查数据
    public boolean check(){
        if("".equals(title.getText())){
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if("".equals(content.getText())){
            Toast.makeText(this, "请输入描述", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }

    public void showSnackBar(String message){
        final Snackbar snackbar=Snackbar.make(getWindow().getDecorView(),message,Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("知道了",new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}
