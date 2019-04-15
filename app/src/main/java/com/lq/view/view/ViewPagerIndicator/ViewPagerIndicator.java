package com.lq.view.view.ViewPagerIndicator;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author : lqhunter
 * date : 2019/4/13 0013
 * description :
 */
public class ViewPagerIndicator extends LinearLayout {


    private static final String TAG = "ViewPagerIndicator";
    //内部view的布局属性
    private LayoutParams mLayoutParams;

    private List<ViewPagerIndicatorTab> indicatorList = new ArrayList<>();


    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mLayoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);

    }

    public void addMyView(ViewPagerIndicatorTab textView) {
        textView.setLayoutParams(mLayoutParams);
        addView(textView);
        indicatorList.add(textView);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
    }

    public ViewPagerIndicatorTab getView(int i) {
        return indicatorList.get(i);
    }

    public void onPageScrolled(int position, float offsetFraction) {

        //Log.d(TAG, "position-->" + position + "偏移百分比-->" + offsetFraction);


        ViewPagerIndicatorTab left = indicatorList.get(position);
        left.setCurrentDirection(ViewPagerIndicatorTab.Direction.BLACK_RED);
        left.setProgress(1 - offsetFraction);

        if (position != indicatorList.size() - 1) {
            ViewPagerIndicatorTab right = indicatorList.get(position + 1);
            right.setCurrentDirection(ViewPagerIndicatorTab.Direction.RED_BLACK);
            right.setProgress(offsetFraction);
        }


        //下面2个if判断解决滑动过快时红色残留的bug
        if (position - 1 >= 0 && position - 1 <indicatorList.size()) {
            ViewPagerIndicatorTab preLeft = indicatorList.get(position - 1);
            if (preLeft.getProgress() != 0) {//如果已经将红色消去了，就不再设置，避免再重绘，降低效率
                preLeft.setCurrentDirection(ViewPagerIndicatorTab.Direction.BLACK_RED);
                preLeft.setProgress(0);
            }

        }

        if (position + 2 >= 0 && position + 2 <indicatorList.size()) {
            ViewPagerIndicatorTab postRight = indicatorList.get(position + 2);
            if (postRight.getProgress() != 0) {
                postRight.setCurrentDirection(ViewPagerIndicatorTab.Direction.RED_BLACK);
                postRight.setProgress(0);
            }

        }
    }


    public void setCurrentSelected(int position) {
        Log.d(TAG, "当前选中-->" + position);
        for (int i = 0; i < indicatorList.size(); i++) {
            if (i == position) {
                indicatorList.get(i).setIsSelected(true);
            } else
                indicatorList.get(i).setIsSelected(false);

        }
    }
}
