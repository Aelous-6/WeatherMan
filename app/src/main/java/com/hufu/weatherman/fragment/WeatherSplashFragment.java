package com.hufu.weatherman.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hufu.weatherman.R;
import com.hufu.weatherman.view.shimmer.Shimmer;
import com.hufu.weatherman.view.shimmer.ShimmerTextView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class WeatherSplashFragment extends BaseFragment {
    private static final String TAG = WeatherSplashFragment.class.getSimpleName();
    private GifImageView gif;
    private GifImageView gifImageView;
    private GifDrawable gifFromAssets;
    private ShimmerTextView shimmerTextView;
    private Shimmer shimmer;

    public WeatherSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_splash, container, false);
        gifImageView = (GifImageView) view.findViewById(R.id.circle_gif);
        shimmerTextView = (ShimmerTextView) view.findViewById(R.id.shimmer_tv);
        try {

            gifFromAssets = new GifDrawable(getActivity().getAssets(), "animated-weather-image-0012.gif");
            gifImageView.setImageDrawable(gifFromAssets);
        } catch (IOException e) {
            e.printStackTrace();
            //GIF 资源打开失败，显示默认失败图片

        }
        shimmer = new Shimmer();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (gifFromAssets != null) {
            Log.i(TAG, "onResume");
            gifFromAssets.start();
        }
        if (shimmer != null) {
            shimmer.start(shimmerTextView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        if (gifFromAssets != null)
            gifFromAssets.stop();
        if (shimmer != null)
            shimmer.cancel();
    }

    @Override
    public void onStart() {
        super.onStart();
        //在此处曾经出现过一次异常
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
