package com.example.yuanshuai.find.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.adapter.ViewPagerAdapter;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ButterKnife.bind(this);
        init();
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



    }
}
