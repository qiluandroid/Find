package com.example.yuanshuai.find.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuanshuai.find.R;
import com.example.yuanshuai.find.fragment.ExchangeFragment;
import com.example.yuanshuai.find.fragment.IndexFragment;
import com.example.yuanshuai.find.fragment.MeFragment;
import com.example.yuanshuai.find.fragment.MessageFragment;

/**
 * Created by yuanshuai on 2017/9/13.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final int Counts=4;
    private IndexFragment indexFragment=new IndexFragment();
    private ExchangeFragment exchangeFragment=new ExchangeFragment();
    private MessageFragment messageFragment=new MessageFragment();
    private MeFragment meFragment=new MeFragment();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                return  indexFragment;
            case 1:
                return exchangeFragment;
            case 2:
                return messageFragment;
            case 3:
                return meFragment;
            default:
                fragment=new Fragment();
                return fragment;

        }

    }

    @Override
    public int getCount() {
        return Counts;
    }
    public void flush(){
        meFragment.flush();
    }
}
