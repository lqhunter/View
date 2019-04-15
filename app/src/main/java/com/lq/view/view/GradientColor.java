package com.lq.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : lqhunter
 * date : 2019/4/9 0009
 * description :
 */
public class GradientColor extends View {

    private Paint mPaint = new Paint();
    private int mColor = Color.RED;

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
        invalidate();
    }

    public GradientColor(Context context) {
        this(context, null);
    }

    public GradientColor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientColor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(mColor);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getHeight(), getWidth()) / 2;
        canvas.drawCircle(centerX, centerY, radius, mPaint);
    }


}
