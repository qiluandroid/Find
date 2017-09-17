package com.example.yuanshuai.find.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuanshuai.find.fragment.ExchangeFragment;
import com.example.yuanshuai.find.fragment.IndexFragment;
import com.example.yuanshuai.find.fragment.MeFragment;
import com.example.yuanshuai.find.fragment.MessageFragment;

/**
 * Created by yuanshuai on 2017/9/13.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final int Counts=4;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new IndexFragment();
                break;
            case 1:
                fragment=new ExchangeFragment();
                break;
            case 2:
                fragment=new MessageFragment();
                break;
            case 3:
                fragment=new MeFragment();
                break;
            default:
                fragment=new Fragment();
                break;

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return Counts;
    }
}
