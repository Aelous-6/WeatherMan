package com.hufu.weatherman.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hufu.weatherman.R;

/**
 * the three  Fragment for spalsh
 */
public class AnimSplashFragment extends BaseFragment {


    public AnimSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO 界面设计和功能实现
        return inflater.inflate(R.layout.fragment_anim_splash, container, false);
    }

}
