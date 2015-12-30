package com.hufu.weatherman.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Administrator on 2015/12/30.
 */
public class WindowUtil {
    public static void hideStatusBar(Activity activity) {
        //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(attrs);

//            tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimary));
//            tintManager.setStatusBarTintEnabled(true);
    }
}
