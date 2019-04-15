package com.lq.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : lqhunter
 * date : 2019/3/29 0029
 * description : 水波纹
 */
public class WaterWave extends View {

    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 400;//波长
    private int mWaveHeight = 100;//波长
    private int waveRefreshSpeed = 16;//两次刷新间间隔时间
    private int mOffsetX = 0;//每次onDraw()相对开始增量
    private float mDx = 10;//每次相对上一次增量
    private int mOriginY = 300;

    /**
     * 设置波长
     *
     * @param itemWaveLength
     */
    public void setItemWaveLength(int itemWaveLength) {
        mItemWaveLength = itemWaveLength;
    }

    /**
     * 设置幅度(总高度)
     *
     * @param waveHeight
     */
    public void setWaveHeight(int waveHeight) {
        mWaveHeight = waveHeight;
    }

    /**
     * 设置增量，当 waveRefreshSpeed 一定时，dx越大，两次刷新间隔越大，波浪流动越快
     *
     * @param dx 大于 1
     */
    public void setDx(float dx) {
        mDx = dx;
    }

    /**
     * 波浪轴线 y 坐标
     *
     * @param originY
     */
    public void setOriginY(int originY) {
        mOriginY = originY;
    }

    public WaterWave(Context context) {
        this(context, null);
    }

    public WaterWave(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterWave(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(new Runnable() {
            @Override
            public void run() {
                if (mOffsetX >= mItemWaveLength) {
                    mOffsetX = 0;
                }
                mOffsetX += mDx;
                invalidate();
                postDelayed(this, waveRefreshSpeed);//设定波浪滚动时间
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int halfWaveLen = mItemWaveLength / 2;
        mPath.moveTo(-mItemWaveLength + mOffsetX, mOriginY);//屏幕左边多画出一个周期波长
        for (int i = -mItemWaveLength; i <= mItemWaveLength + getWidth(); i += mItemWaveLength) {
            //偏移值
            mPath.rQuadTo(halfWaveLen / 2, -(mWaveHeight / 2), halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen / 2, mWaveHeight / 2, halfWaveLen, 0);
        }

        //封闭
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, mPaint);
    }


}
