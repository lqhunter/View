package com.lq.view.HelpView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : lqhunter
 * date : 2019/4/2 0002
 * description :
 */
public class ScaleOneBg extends View {

    Paint mPaint;

    public ScaleOneBg(Context context) {
        this(context, null);
    }

    public ScaleOneBg(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleOneBg(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        //mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(50, 50,  50 + 120, 50 + 150, mPaint);

        canvas.drawRect((float)(getWidth() / 2.0 - 0.15*getWidth()),
                (float)(getHeight()/2.0 - 0.15*getHeight()),
                (float)(getWidth()/2.0 + 0.15*getWidth()),
                (float)(getHeight()/2.0 + 0.15*getHeight()),
                mPaint);

        canvas.drawRect(1, 1, getWidth(), getHeight(), mPaint);

    }
}
