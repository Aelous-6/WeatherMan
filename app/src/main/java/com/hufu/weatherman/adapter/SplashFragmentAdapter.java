package com.hufu.weatherman.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hufu.weatherman.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public class SplashFragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> list;
    public SplashFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public SplashFragmentAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
