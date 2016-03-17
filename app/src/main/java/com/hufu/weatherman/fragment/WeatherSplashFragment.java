package com.hufu.weatherman.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.ViewSwitcher;

import com.hufu.weatherman.R;
import com.hufu.weatherman.view.shimmer.Shimmer;
import com.hufu.weatherman.view.shimmer.ShimmerTextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class WeatherSplashFragment extends BaseFragment {
    private static final String TAG = WeatherSplashFragment.class.getSimpleName();
    private GifImageView gifImageView;
    private GifDrawable gifFromAssets;
    private ShimmerTextView shimmerTextView;
    private Shimmer shimmer;
    private Timer timer;
    private TextSwitcher text_switcher;

    public WeatherSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_splash, container, false);
        gifImageView = (GifImageView) view.findViewById(R.id.circle_gif);

        text_switcher = (TextSwitcher) view.findViewById(R.id.text_switcher);
        //((ViewGroup)text_switcher.getParent()).removeView(text_switcher);
        text_switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                // TODO: 2016/3/17  don't understand this question !
                //下面这段语句放在外边或出现 has parent ，please before removeView()
                shimmerTextView = new ShimmerTextView(getContext());

                shimmerTextView.setReflectionColor(getResources().getColor(R.color.shimmer_textView_color));
                return shimmerTextView;
            }
        });

        try {
            gifFromAssets = new GifDrawable(getActivity().getAssets(), "animated-weather-image-0012.gif");
            gifImageView.setImageDrawable(gifFromAssets);
        } catch (IOException e) {
            e.printStackTrace();
            //GIF 资源打开失败，显示默认失败图片
            gifImageView.setImageResource(R.drawable.load_error);
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

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                text_switcher.setInAnimation(getContext(), R.anim.slide_in_bottom);
                text_switcher.setOutAnimation(getContext(), R.anim.slide_out_up);
                //修改UI必须要切换线程到main线程
                refreshUI();
            }
        }, 100l, 3000l);
    }

    private void refreshUI() {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text_switcher.setText(getString(R.string.shimmer_text));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        if (gifFromAssets != null)
            gifFromAssets.stop();
        if (shimmer != null)
            shimmer.cancel();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
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
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void startAnimation() {
    }

    @Override
    public void stopAnimation() {
    }
}
