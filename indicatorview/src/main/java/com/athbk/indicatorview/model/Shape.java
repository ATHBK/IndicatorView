package com.athbk.indicatorview.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by athbk on 3/17/17.
 */

public class Shape implements Indicator {

    private Paint mPaint;
    private int color;
    private RectF rectF;

    public Shape(int color){
        this.color = color;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
    }

    public void setColor(int color) {
        this.color = color;
        mPaint.setColor(color);
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }

    @Override
    public void draw(Canvas canvas) {
        if (mPaint == null) return;
        canvas.drawRect(rectF, mPaint);
    }
}
