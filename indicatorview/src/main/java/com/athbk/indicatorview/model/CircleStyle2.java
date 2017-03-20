package com.athbk.indicatorview.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by athbk on 3/17/17.
 */

public class CircleStyle2 implements Indicator {

    private int radius;
    private PointF pointF;
    private boolean isSelected;
    private Paint mPaint;
    private int color;

    public CircleStyle2(int radius, int color, boolean isSelected) {
        this.radius = radius;
        this.color = color;
        this.isSelected = isSelected;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setPointF(PointF pointF) {
        this.pointF = pointF;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setColor(int color, boolean isSelected) {
        this.color = color;
        setSelected(isSelected);
        mPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        if (pointF == null) return;
        if (isSelected){
            mPaint.setStyle(Paint.Style.FILL);
        }
        else {
            mPaint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawCircle(pointF.x, pointF.y, radius, mPaint);
    }
}
