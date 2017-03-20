package com.athbk.indicatorview.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by athbk on 3/17/17.
 */

public class CircleStyle1 implements Indicator{
    private int radius;
    private PointF pointF;
    private Paint mPaint;
    private int color;

    public CircleStyle1(int radius, int color) {
        this.radius = radius;
        this.color = color;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColor(int color) {
        this.color = color;
        mPaint.setColor(color);
    }

    public void setPointF(PointF pointF) {
        this.pointF = pointF;
    }

    @Override
    public void draw(Canvas canvas) {
        if (pointF == null) return;
        canvas.drawCircle(pointF.x, pointF.y, radius, mPaint);
    }

}
