package com.hufu.weatherman.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.WindowManager;
import android.widget.TextView;

import com.hufu.weatherman.R;
import com.hufu.weatherman.fragment.ForecastFragment;
import com.hufu.weatherman.fragment.LifeFragment;
import com.hufu.weatherman.fragment.LiveFragment;
import com.hufu.weatherman.fragment.MeFragment;
import com.hufu.weatherman.manager.SystemBarTintManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private SystemBarTintManager tintManager;
    private TabLayout mtabLayout;
    private ArrayList<String> titles;
    private ArrayList<String> etitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWindows();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mtabLayout = (TabLayout) findViewById(R.id.tabs);
        mtabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        initView();
    }

    private void initView() {
        titles = new ArrayList<>(4);
        titles.add("预报");
        titles.add("生活");
        titles.add("实景");
        titles.add("我");

        etitles = new ArrayList<>(4);
        etitles.add("Forecast");
        etitles.add("Life");
        etitles.add("Live");
        etitles.add("Me");

//        mtabLayout.addTab(mtabLayout.newTab().setText(titles.get(0)));
//        mtabLayout.addTab(mtabLayout.newTab().setText(titles.get(1)));
//        mtabLayout.addTab(mtabLayout.newTab().setText(titles.get(2)));

        //  mtabLayout.setTabsFromPagerAdapter();
        mtabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mtabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mtabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(mSectionsPagerAdapter.getTabView(i));
            }
        }
        //当直接使用下面的第二句语句时，当界面被打开时并没有选择第0项, 但是改变参数为非0的就行，不明白？
        //用了下面很笨的方法，先设为其他位置，然后在设置一次，能解决这个问题。
        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);
    }


    @TargetApi(19)
    private void initWindows() {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);

//            tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary));
//            tintManager.setStatusBarTintEnabled(true);

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            /**
             * 根据位置不同的fragment
             */
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new ForecastFragment();
                    break;
                case 1:
                    fragment = new LifeFragment();
                    break;
                case 2:
                    fragment = new LiveFragment();
                    break;
                case 3:
                    fragment = new MeFragment();
                    break;
            }
            return fragment;
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.tablayout_title, null);
            TextView tv = (TextView) v.findViewById(R.id.china);
            tv.setText(titles.get(position));
            TextView tv2 = (TextView) v.findViewById(R.id.english);
            tv2.setText(etitles.get(position));
            return v;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
            }
            return null;
        }
    }
}
