package com.hufu.weatherman.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.hufu.weatherman.R;
import com.hufu.weatherman.adapter.SplashFragmentAdapter;
import com.hufu.weatherman.fragment.AnimSplashFragment;
import com.hufu.weatherman.fragment.BaseFragment;
import com.hufu.weatherman.fragment.VideoSplashFragment;
import com.hufu.weatherman.fragment.WeatherSplashFragment;
import com.hufu.weatherman.utils.WindowUtil;
import com.hufu.weatherman.view.CircleIndicator;
import com.hufu.weatherman.view.GuideViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    //  public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    private static final String TAG = SplashActivity.class.getSimpleName();
    //用于存放点图片的数组
    private ImageView[] dots;
    //用于存放当前ViewPager中fragment的集合
    private List<BaseFragment> fragmentLists = new ArrayList<>();
    private SplashFragmentAdapter splashFragmentAdapter;
    private int currentSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        WindowUtil.hideStatusBar(this);
        /**
         * 使用ViewPager+fragment做引导页
         */
        //初始化页面的点
        // initData();

        //获取自定义ViewPager，设置背景图片
        GuideViewPager viewpager = (GuideViewPager) findViewById(R.id.splash_viewpager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        viewpager.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.bg_splash));

        //初始化fragment 设置适配器,
        fragmentLists.add(new WeatherSplashFragment());
        fragmentLists.add(new VideoSplashFragment());
        fragmentLists.add(new AnimSplashFragment());

        splashFragmentAdapter = new SplashFragmentAdapter(getSupportFragmentManager(), fragmentLists);

        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(pageChangeListener);
        viewpager.setAdapter(splashFragmentAdapter);

        indicator.setViewPager(viewpager);
    }

    private void initData() {
        ArrayList<View> viewList = new ArrayList<View>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            View view = new View(this);
            view.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
            viewList.add(view);
        }
    }

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            //设置背景图片
            //setImageBackground(position);
            //停止上个页面的内容
            fragmentLists.get(currentSelect).stopAnimation();
            //开启要打开的页面的内容
            fragmentLists.get(position).startAnimation();
            //改变当前选中也的值
            currentSelect = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

}
