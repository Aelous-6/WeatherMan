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
import android.view.View;

import com.hufu.weatherman.R;

/**
 * TODO: document your custom view class.
 */
public class GuideViewPager extends ViewPager {
    private Bitmap background;
    private Paint paint = new Paint(1);

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

    public void setBackground(Bitmap background) {
        this.background = background;
        this.paint.setFilterBitmap(true);
    }
}
