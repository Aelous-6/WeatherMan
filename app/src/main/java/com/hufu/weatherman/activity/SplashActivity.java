package com.hufu.weatherman.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hufu.weatherman.R;
import com.hufu.weatherman.adapter.SplashFragmentAdapter;
import com.hufu.weatherman.fragment.AnimSplashFragment;
import com.hufu.weatherman.fragment.BaseFragment;
import com.hufu.weatherman.fragment.VideoSplashFragment;
import com.hufu.weatherman.fragment.WeatherSplashFragment;
import com.hufu.weatherman.utils.WindowUtil;
import com.hufu.weatherman.view.GuideViewPager;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    //用于存放点图片的数组
    private ImageView[] dots;
    //用于存放当前ViewPager中fragment的集合
    private List<BaseFragment> fragmentLists = new ArrayList<>();
    private SplashFragmentAdapter splashFragmentAdapter;
    private int currentSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        WindowUtil.hideStatusBar(this);
        /**
         * 使用ViewPager+fragment做引导页
         */
        //初始化页面的点
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.dot_group);

        dots = new ImageView[3];
        for (int i = 0; i < dots.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(6, 6));

            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            dots[i] = imageView;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 16;
            layoutParams.rightMargin = 16;
            viewGroup.addView(imageView, layoutParams);
        }
        //获取自定义ViewPager，设置背景图片
        GuideViewPager viewpager = (GuideViewPager) findViewById(R.id.splash_viewpager);
        viewpager.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.bg_splash));
        //初始化fragment 设置适配器,
        fragmentLists.add(new WeatherSplashFragment());
        fragmentLists.add(new VideoSplashFragment());
        fragmentLists.add(new AnimSplashFragment());

        splashFragmentAdapter = new SplashFragmentAdapter(getSupportFragmentManager(), fragmentLists);
        viewpager.setAdapter(splashFragmentAdapter);
        viewpager.setCurrentItem(0);
        viewpager.addOnPageChangeListener(pageChangeListener);
    }


    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            //设置背景图片
            setImageBackground(position);
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

    /**
     * 设置指定位置点的背景图片
     *
     * @param position
     */
    private void setImageBackground(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                dots[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
}
