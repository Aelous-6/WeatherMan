package com.hufu.weatherman.fragment;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hufu.weatherman.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class WeatherSplashFragment extends BaseFragment {
    private static final String TAG = WeatherSplashFragment.class.getSimpleName();
    private GifImageView gif;
    private GifImageView gifImageView;
    private GifDrawable gifFromAssets;

    public WeatherSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_splash, container, false);
        gifImageView = (GifImageView) view.findViewById(R.id.circle_gif);
        try {

            gifFromAssets = new GifDrawable(getActivity().getAssets(), "animated-weather-image-0012.gif");
            gifImageView.setImageDrawable(gifFromAssets);
        } catch (IOException e) {
            e.printStackTrace();
            //GIF 资源打开失败，显示默认失败图片

        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gifFromAssets != null) {
            Log.i(TAG, "onResume");
            gifFromAssets.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        gifFromAssets.stop();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!gifFromAssets.isRunning()) {
            Log.i(TAG, "onStart");
            gifFromAssets.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (gifFromAssets.isRunning()) {
            Log.i(TAG, "onStop");
            gifFromAssets.stop();
            gifFromAssets = null;
        }
    }

    @Override
    public void startAnimation() {
    }

    @Override
    public void stopAnimation() {
    }
}
