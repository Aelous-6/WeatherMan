package com.hufu.weatherman.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hufu.weatherman.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimSplashFragment extends BaseFragment {


    public AnimSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anim_splash, container, false);
    }

}
