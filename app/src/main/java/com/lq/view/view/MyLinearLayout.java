package com.lq.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * author : lqhunter
 * date : 2019/4/20 0020
 * description :
 */
public class MyLinearLayout extends ViewGroup {


    private static final String TAG = "MyLinearLayout";

    //在代码里面new的时候调用
    public MyLinearLayout(Context context) {
        super(context);
    }

    //在布局layout使用时调用
    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //在布局layout使用时调用,并且有 style 属性时
    /*  <com.lq.myview.MyTextView
            style="@style/MyTextView"
            android:text="hello world!" />
    */
    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int count = getChildCount();
        

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);


            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            int measuredHeight = child.getMeasuredHeight();
            int measuredWidth = child.getMeasuredWidth();
            Log.d(TAG, "测量后第" + i + "个child高度-->" + measuredHeight);
            height += measuredHeight;
            width = measuredWidth > width ? measuredWidth : width;
            Log.d(TAG, "测量后第" + i + "个child宽度-->" + width);

        }

        Log.d(TAG, "最终的高度-->" + height);

        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : height);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            child.layout(0, top, width, top + height);
            top += height;
        }
    }
}
