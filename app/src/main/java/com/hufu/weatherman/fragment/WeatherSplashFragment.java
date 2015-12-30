package com.hufu.weatherman.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hufu.weatherman.R;

import pl.droidsonroids.gif.GifImageView;

public class WeatherSplashFragment extends BaseFragment {
    private GifImageView gif;
    public WeatherSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_splash, container, false);
        gif = (GifImageView) view.findViewById(R.id.gif);
        return view;
    }



    @Override
    public void startAnimation() {
    }

    @Override
    public void stopAnimation() {
    }
}
