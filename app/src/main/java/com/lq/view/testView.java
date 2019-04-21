package com.lq.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author : lqhunter
 * date : 2019/4/20 0020
 * description :
 */
public class testView extends View {


    private static final String TAG = "testView";

    public testView(Context context) {
        this(context, null);
    }

    public testView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public testView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthValue = MeasureSpec.getSize(widthMeasureSpec);
        int heightValue = MeasureSpec.getSize(heightMeasureSpec);


        Log.d(TAG, "widthMode-->" + widthMode);
        Log.d(TAG, "widthValue-->" + widthValue);
        Log.d(TAG, "heightMode-->" + heightMode);
        Log.d(TAG, "heightValue-->" + heightValue);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);




    }
}
