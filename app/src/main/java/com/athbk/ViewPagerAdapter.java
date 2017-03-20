package com.athbk;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by athbk on 3/20/17.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private String[] colors = {"#FFFF00", "#FF0000", "#008000", "#0000FF"};
    private int count = 5;

    public ViewPagerAdapter(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item, container, false);
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.layout);
        int index = position;
        if (position >= colors.length) {
            index = position % colors.length;
        }
        layout.setBackgroundColor(Color.parseColor(colors[index]));
        container.addView(view);
        return view;
    }
}
