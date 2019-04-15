package com.lq.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * author : lqhunter
 * date : 2019/3/26 0026
 * description : 画一个时钟
 */
public class Clock extends View {

    private static final String TAG = "clock";
    private Paint mCirclePaint;
    private Paint mKeduPaint;
    private int mCircleCenterX;
    private int mCirclrCenterY;
    private int mRadius;
    private int mKeduX;
    private int mStartY;
    private int mEndY;
    private Paint mTextPaint;
    private int mHour;
    private int mMinute;
    private int mSecond;

    public Clock(Context context) {
        this(context, null);
    }

    public Clock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Clock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initCirclePaint();//初始化圆形paint
        initKeduPaint();//初始化刻度paint
        initTextPaint();

    }


    private void initCircleData() {
        //圆的坐标数据
        mCircleCenterX = getWidth() / 2;
        mCirclrCenterY = getHeight() / 2;
        mRadius = (int) ((getWidth() / 2) * 0.9);//取一半宽度的9/10作为半径

        //12点方向刻度坐标数据
        mKeduX = mCircleCenterX;
        mStartY = mCirclrCenterY - 9 * (mRadius / 10);
        mEndY = mCirclrCenterY - mRadius;
    }


    private void initTextPaint() {
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.parseColor("#000000"));//设置颜色
        mTextPaint.setStrokeWidth(3);//设置线宽度
        mTextPaint.setStyle(Paint.Style.FILL);//设置只绘制图形轮廓（描边）
        //该方法即为设置基线上那个点到底是left,center,还是right  这里我设置为center
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initCirclePaint() {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.parseColor("#000000"));//设置颜色
        mCirclePaint.setStrokeWidth(5);//设置线宽度
        mCirclePaint.setStyle(Paint.Style.STROKE);//设置只绘制图形轮廓（描边）
    }

    private void initKeduPaint() {
        //准备画笔Paint
        mKeduPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mKeduPaint.setColor(Color.parseColor("#000000"));
        mKeduPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        post(new Runnable() {
            @Override
            public void run() {
                invalidate();
                postDelayed(this, 1000);

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw...start");
        super.onDraw(canvas);
        initCircleData();

        //画圆
        canvas.drawCircle(mCircleCenterX, mCirclrCenterY, mRadius, mCirclePaint);
        //画圆心
        mCirclePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mCircleCenterX, mCirclrCenterY, 10, mCirclePaint);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        int k = 12;
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {//除得尽的为大刻度

                mKeduPaint.setStrokeWidth(8);
                canvas.drawLine(mKeduX, mStartY, mKeduX, mEndY, mKeduPaint);

                if (k > 12) k = 1;
                String num = String.valueOf(k++);
                mTextPaint.setTextSize(mRadius / 10);
                //Log.d(TAG, "字体大小 ---> " + mRadius / 10);
                canvas.drawText(num, mKeduX, mCirclrCenterY - mRadius + 2 * (mRadius / 10), mTextPaint);


            } else {//除不尽为小刻度
                mKeduPaint.setStrokeWidth(3);
                canvas.drawLine(mKeduX, mStartY, mKeduX, mEndY, mKeduPaint);
            }

            canvas.rotate(6, getWidth() / 2, getHeight() / 2);
        }

        //获取当前时间 如：19:18:30
        getTime();
        //计算时分秒角度
        int hourDegrees = 30 * (mHour % 12) + (int) (30 * (mMinute / 60.0));
        int minuteDegrees = 6 * mMinute;
        int secondDegrees = 6 * mSecond;

        //画时针
        mKeduPaint.setStrokeWidth(12);
        canvas.rotate(hourDegrees, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, mCirclrCenterY - 5 * (mRadius / 10), mKeduPaint);
        canvas.rotate(hourDegrees * (-1), getWidth() / 2, getHeight() / 2);


        //画分针
        mKeduPaint.setStrokeWidth(8);
        canvas.rotate(minuteDegrees, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, mCirclrCenterY - 7 * (mRadius / 10), mKeduPaint);
        canvas.rotate(minuteDegrees * (-1), getWidth() / 2, getHeight() / 2);

        //画秒针
        mKeduPaint.setStrokeWidth(3);
        mKeduPaint.setColor(Color.RED);
        canvas.rotate(secondDegrees, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, mCirclrCenterY - 8 * (mRadius / 10), mKeduPaint);
        canvas.rotate(secondDegrees * (-1), getWidth() / 2, getHeight() / 2);
        mKeduPaint.setColor(Color.BLACK);

    }

    private void getTime() {
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        int year = calendar.get(Calendar.YEAR);
        //月
        int month = calendar.get(Calendar.MONTH) + 1;
        //日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //获取系统时间
        //小时
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        mMinute = calendar.get(Calendar.MINUTE);
        //秒
        mSecond = calendar.get(Calendar.SECOND);
        Log.d(TAG, "time-->" + mHour + ":" + mMinute + ":" + mSecond);
    }

}

