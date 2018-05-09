package com.example.yuanshuai.find.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.ViewPagerAdapter;
import com.example.yuanshuai.find.model.Mission;
import com.example.yuanshuai.find.model.Output;
import com.example.yuanshuai.find.net.Net;
import com.example.yuanshuai.find.tool.LocationUtils;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.graphics.BitmapFactory.decodeStream;
import static java.security.AccessController.getContext;

public class Main extends AppCompatActivity {
    //view
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.mytab)
    TabLayout tabLayout;
    @OnClick(R.id.add)
    public void add(){
        Intent intent=new Intent(Main.this,Send.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in,R.anim.out);
    }


    private ViewPagerAdapter viewPagerAdapter;
    private final int COUNTS=4;
    private int[] tabtitle=new int[]{R.string.tabtitle1,R.string.tabtitle2,R.string.tabtitle3,R.string.tabtitle4};
    private int[] tabimage=new int[]{R.drawable.index,R.drawable.exchange,R.drawable.message,R.drawable.me};
    private int last=0;
    final private int REQUEST_CODE_LOCAL=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ButterKnife.bind(this);
        init();
        initViews();
    }
    private void init(){
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        setTabs();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("po",""+position);
                if(position<2) {
                    tabLayout.getTabAt(position).select();
                    last=position;
                }
                else {
                    tabLayout.getTabAt(position + 1).select();
                    last=position+1;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        viewPager.setCurrentItem(0);
                        last=0;
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        last=1;
                        break;
                    case 2:
                        /*
                        * 发布*/
                        tabLayout.getTabAt(last).select();

                        break;
                    case 3:
                        viewPager.setCurrentItem(2);
                        last=3;
                        break;
                    case 4:
                        viewPager.setCurrentItem(3);
                        last=4;
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        获取地理位置

    }
    private void setTabs(){
        for(int i=0;i<COUNTS;i++){
            TabLayout.Tab tab=tabLayout.newTab();
            View view=getLayoutInflater().inflate(R.layout.tab_item,null);

            tab.setCustomView(view);

            TextView tab_text=ButterKnife.findById(view,R.id.tabItemText);
            ImageView tab_img=ButterKnife.findById(view,R.id.tabItemImage);
            tab_text.setText(tabtitle[i]);
            tab_img.setImageResource(tabimage[i]);
            tabLayout.addTab(tab);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);

        }
        View view=getLayoutInflater().inflate(R.layout.tab_item,null);

        TextView tab_text=ButterKnife.findById(view,R.id.tabItemText);
        ImageView tab_img=ButterKnife.findById(view,R.id.tabItemImage);
        tab_text.setText("发布");
        TabLayout.Tab tab=tabLayout.newTab().setCustomView(view);
        tab.getCustomView().setBackground(null);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view),2,false);

        getLocate();

    }
    private void initViews(){
        Log.e("head","0");
        Net.getNet().getUserAvatar()
                .subscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap call(ResponseBody responseBody) {
                        Bitmap bitmap=null;
                        try {
                            Log.e("head","1");
                            Net.getNet().setBs(responseBody.bytes());
                            Log.e("head","2");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bitmap= BitmapFactory.decodeStream(responseBody.byteStream());
                        return bitmap;

                    }
                })
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        Drawable draw=new BitmapDrawable(bitmap);
                        Net.getNet().setHeadImage(draw);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showSnackBar(throwable.getMessage());
                        Log.e("error",throwable.getMessage());
                    }
                });
        flush();

    }
//    刷新列表
    public void flush(){
        Location location=getLocate();
        double a=location.getLatitude();
        double b=location.getLongitude();
        Net.getNet().nearbyList(0,location.getLatitude(),location.getLongitude(),10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Output<List<Mission>>>() {
                    @Override
                    public void call(Output<List<Mission>> listOutput) {
                        Log.e("f",""+listOutput.getData().size());
//                        放到recycleview上
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        showSnackBar(throwable.getMessage());
                    }
                });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE_LOCAL:
                if(data!=null){
                    final Uri uri = data.getData();
                    String path=getPath(uri);
                    Log.e("a",path);
//                设置头像
                    Net.getNet().c(path)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<ResponseBody>() {
                                @Override
                                public void call(ResponseBody output) {
                                    Log.e("a", "" + output.toString());
//                                重新设置一次
                                    Net.getNet().getUserAvatar()
                                            .subscribeOn(Schedulers.io())
                                            .map(new Func1<ResponseBody, Bitmap>() {
                                                @Override
                                                public Bitmap call(ResponseBody responseBody) {
                                                    Bitmap bitmap=null;
                                                    try {
                                                        Net.getNet().setBs(responseBody.bytes());
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    bitmap= BitmapFactory.decodeStream(responseBody.byteStream());



                                                    return bitmap;

                                                }
                                            })
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Action1<Bitmap>() {
                                                @Override
                                                public void call(Bitmap bitmap) {
                                                    Drawable draw=new BitmapDrawable(bitmap);
                                                    Net.getNet().setHeadImage(draw);
                                                    viewPagerAdapter.flush();
                                                }
                                            }, new Action1<Throwable>() {
                                                @Override
                                                public void call(Throwable throwable) {
                                                    showSnackBar(throwable.getMessage());
                                                    Log.e("error",throwable.getMessage());
                                                }
                                            });
                                }

                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    showSnackBar(throwable.getMessage());
                                }
                            });
                }

                break;
            default:
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);

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
//    获取地理位置
    private Location getLocate(){
        return LocationUtils.getInstance(Main.this).showLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance(this).removeLocationUpdatesListener();
    }

}
