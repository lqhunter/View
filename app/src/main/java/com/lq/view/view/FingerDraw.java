package com.lq.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 贝塞尔曲线画图
 * author : lqhunter
 * date : 2019/3/29 0029
 * description :
 */
public class FingerDraw extends View {

    private static final String TAG = "FingerDraw";
    private Path mPath;
    private Paint mPaint;
    private float mPreX;
    private float mPreY;
    private float mStartX;
    private float mStartY;

    public FingerDraw(Context context) {
        this(context, null);
    }

    public FingerDraw(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FingerDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mStartX = event.getX();
                mStartY = event.getY();
                mPath.moveTo(mStartX, mStartY);
                mPreX = mStartX;
                mPreY = mStartY;
                return true;
            }
            case MotionEvent.ACTION_MOVE: {

                //设置贝塞尔曲线的操作点为起点和终点的一半
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;

                //mPath 内包含之前点信息，所以只需要操作点和结束点就能画
                mPath.quadTo(mPreX, mPreY, endX, endY);

                mPreX = event.getX();
                mPreY = event.getY();

                invalidate();
            }
            break;
            default:
                break;
        }
        return super.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);

    }

    public void reset() {
        mPath.reset();
        invalidate();
    }
}
