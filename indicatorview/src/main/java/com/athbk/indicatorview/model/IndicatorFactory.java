package com.athbk.indicatorview.model;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by athbk on 3/20/17.
 */

public class IndicatorFactory {

    private int coloSelected;
    private int colorUnselected;

    public IndicatorFactory(int coloSelected, int colorUnselected) {
        this.coloSelected = coloSelected;
        this.colorUnselected = colorUnselected;
    }

    public Indicator getIndicatorBasic(StyleIndicator type, int radius, boolean isSelected){
        if (type == StyleIndicator.CIRCLE_STYLE_1){
            return new CircleStyle1(radius, isSelected ? coloSelected : colorUnselected);
        }
        else if (type == StyleIndicator.CIRCLE_STYLE_2){
            return new CircleStyle2(radius, isSelected ? coloSelected : colorUnselected, isSelected);
        }
        else {
            return new Shape(isSelected ? coloSelected : colorUnselected);
        }
    }

    public Indicator setPropertyColorIndicator(Indicator in, StyleIndicator type, boolean isSelected){
        Indicator indicator = in;
        if (type == StyleIndicator.CIRCLE_STYLE_1){
            ((CircleStyle1)indicator).setColor(isSelected ? coloSelected : colorUnselected);
        }
        else if (type == StyleIndicator.CIRCLE_STYLE_2){
            ((CircleStyle2)indicator).setColor(isSelected ? coloSelected : colorUnselected, isSelected);
        }
        else {
            ((Shape)indicator).setColor(isSelected ? coloSelected : colorUnselected);
        }
        return indicator;
    }

    public Indicator setPropertyRadiusIndicator(Indicator in, StyleIndicator type, PointF pointF){
        Indicator indicator = in;
        if (type == StyleIndicator.CIRCLE_STYLE_1){
            ((CircleStyle1)indicator).setPointF(pointF);
        }
        else {
            ((CircleStyle2)indicator).setPointF(pointF);
        }
        return indicator;
    }


    public Indicator setPropertyRadiusIndicator(Indicator in, RectF rectF){
        Indicator indicator = in;
        ((Shape)indicator).setRectF(rectF);
        return indicator;
    }


}
