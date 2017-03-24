package com.athbk.indicatorview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.athbk.indicatorview.model.CircleStyle1;
import com.athbk.indicatorview.model.CircleStyle2;
import com.athbk.indicatorview.model.Indicator;
import com.athbk.indicatorview.model.IndicatorFactory;
import com.athbk.indicatorview.model.Shape;
import com.athbk.indicatorview.model.StyleIndicator;

/**
 * Created by athbk on 3/17/17.
 */

public class IndicatorView extends View implements ViewPager.OnPageChangeListener{
    private final StyleIndicator DEFAULT_TYPE = StyleIndicator.CIRCLE_STYLE_1;
    private final int DEFAULT_RADIUS_SELECTED = 10;
    private final int DEFAULT_RADIUS_UNSELECTED = 5;
    private final int DEFAULT_COLOR_SELECTED = Color.parseColor("#ffffff");
    private final int DEFAULT_COLOR_UNSELECTED = Color.parseColor("#40ffffff");
    private final int DEFAULT_WIDTH_SHAPE = 30;
    private final int DEFAULT_DISTANCE = 20;

    private int colorSelected = DEFAULT_COLOR_SELECTED;
    private int colorUnSelected = DEFAULT_COLOR_UNSELECTED;
    private StyleIndicator type = DEFAULT_TYPE;
    /*
        use radius with circle
     */
    private int radius = DEFAULT_RADIUS_SELECTED;

    /*
        use height with shape
     */
    private int height = DEFAULT_RADIUS_SELECTED;
    private int width = DEFAULT_WIDTH_SHAPE;

    private int currentPosition = 0;
    private int beforePosition = 0;

    private IndicatorFactory indicatorFactory;
    private Indicator[] indicators;

    private ViewPager viewPager;

    private Context context;

    public IndicatorView(Context context) {
        super(context);
        this.context = context;
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
        this.context = context;
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (indicators == null) return;
        for (int i=0; i<indicators.length; i++){
            indicators[i].draw(canvas);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h = radius*2;
        if (type == StyleIndicator.SHAPE){
            h = height;
        }
        setMeasuredDimension(resolveSizeAndState(0, widthMeasureSpec, 0), resolveSizeAndState(h, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (viewPager == null) return;
        int haftWidth = getWidth() / 2;
        int count = viewPager.getAdapter().getCount();
        int y = getHeight()/2;
        int d = radius*2;
        if (type == StyleIndicator.SHAPE){
            d = width;
        }
        int firstX = haftWidth - (count / 2) * d - (count / 2) * (DEFAULT_DISTANCE) - d / 2;
        if (count % 2 == 0) {
            firstX = haftWidth - (count / 2 - 1) * d - (count / 2) * (DEFAULT_DISTANCE) - d / 2;
        }
        for (int i = 0; i < count; i++){
            if (type == StyleIndicator.SHAPE){
                float lef = firstX + i*DEFAULT_DISTANCE + i * d;
                float tp = getHeight()/2;
                float rg = lef + d;
                float btm = height;
                RectF rectF = new RectF(lef, tp, rg, btm);
                indicators[i] = indicatorFactory.setPropertyRadiusIndicator(indicators[i], rectF);
            }
            else {
                PointF pointF = new PointF(firstX + i*DEFAULT_DISTANCE + i*d, getHeight()/2);
                indicators[i] = indicatorFactory.setPropertyRadiusIndicator(indicators[i], type, pointF);
            }
        }
        invalidate();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        beforePosition = currentPosition;
        currentPosition = position;
        if (beforePosition == currentPosition) return;
        indicators[beforePosition] = indicatorFactory.setPropertyColorIndicator(indicators[beforePosition], type, false);
        indicators[currentPosition] = indicatorFactory.setPropertyColorIndicator(indicators[currentPosition], type, true);
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView, defStyleAttr, 0);
        colorSelected = ta.getColor(R.styleable.IndicatorView_indi_color_selected, DEFAULT_COLOR_SELECTED);
        colorUnSelected = ta.getColor(R.styleable.IndicatorView_indi_color_unselected, DEFAULT_COLOR_UNSELECTED);
        ta.recycle();

        indicatorFactory = new IndicatorFactory(colorSelected, colorUnSelected);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        int count = viewPager.getAdapter().getCount();
        indicators = new Indicator[viewPager.getAdapter().getCount()];
        for (int i = 0; i < count; i++){
            indicators[i] = indicatorFactory.getIndicatorBasic(type, radius, i == 0);
        }
    }

    public void setType(StyleIndicator type) {
        this.type = type;
        requestLayout();
    }

    /**
     *
     * @param height dp
     */
    public void setHeightShape(int height) {
        this.height = (int) DeviceDimensionsHelper.convertDpToPixel(height, context);
        requestLayout();
    }

    /**
     *
     * @param width dp
     */
    public void setWidthShape(int width) {
        this.width = (int) DeviceDimensionsHelper.convertDpToPixel(width, context);
        invalidate();
    }
}
