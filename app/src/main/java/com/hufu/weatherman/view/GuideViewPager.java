package com.hufu.weatherman.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.hufu.weatherman.R;

import java.lang.reflect.Field;

/**
 * TODO: document your custom view class.
 */
public class GuideViewPager extends ViewPager {
    private static final String TAG = GuideViewPager.class.getSimpleName();

    private Bitmap background;
    private Paint paint = new Paint(1);
    private int duration = 1000;

    public GuideViewPager(Context context) {
        super(context);
    }

    public GuideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (this.background != null) {
            //当设置了背景时
            int width = this.background.getWidth();
            int height = this.background.getHeight();
            int count = getAdapter().getCount();
            int x = getScrollX();
            int n = height * getWidth() / getHeight();

            int w = x * ((width - n) / (count - 1)) / getWidth();
            canvas.drawBitmap(this.background, new Rect(w, 0, n + w, height), new Rect(x, 0, x + getWidth(), getHeight()), this.paint);
        }
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "onInterceptTouchEvent in IllegalArgumentException");
            return false;
        }
    }
    public void setBackground(Bitmap background) {
        this.background = background;
        this.paint.setFilterBitmap(true);
    }
}
