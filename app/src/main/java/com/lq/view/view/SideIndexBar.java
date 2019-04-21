package com.lq.view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lq.view.R;

/**
 * author : lqhunter
 * date : 2019/4/20 0020
 * description :
 */
public class SideIndexBar extends View {
    private static final String TAG = "SliddingMenu";

    private static final String[] letter = {"A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Paint mPaint;
    private float mTextSize;
    private int mTextColor;
    private OnLetterListener listener;
    private int mCurrentPosition;

    public SideIndexBar(Context context) {
        this(context, null);
    }

    public SideIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SideIndexBar);

        mTextSize = array.getDimension(R.styleable.SideIndexBar_textSize, 20);
        mTextColor = array.getColor(R.styleable.SideIndexBar_textColor, Color.BLACK);
        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //计算高度 = 左右Padding值 + 字母宽度
        int letterWidth = (int) mPaint.measureText("A");
        int width = getPaddingLeft() + getPaddingRight() + letterWidth;

        //match_parent高度直接获取
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = getHeight();
        //每个字母高度
        int singleHeight = getHeight() / 26;

        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        int baseline = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.descent + singleHeight / 2;
        int drawX = 0;
        for (int i = 0; i < 26; i++) {

            drawX = getWidth() / 2 - (int) mPaint.measureText(letter[i]) / 2;

            canvas.drawText(letter[i], drawX, baseline, mPaint);
            baseline += singleHeight;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        mCurrentPosition = (int) (y / (getHeight() / 26));
        if (mCurrentPosition > 25)
            mCurrentPosition = 25;
        if (mCurrentPosition < 0)
            mCurrentPosition = 0;
        Log.d(TAG, "mCurrentPosition-->" + mCurrentPosition);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.clickDown(mCurrentPosition, letter[mCurrentPosition]);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (listener != null) {
                    listener.move(mCurrentPosition, letter[mCurrentPosition]);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.clickUp(mCurrentPosition);
                }
                break;
        }


        return super.onTouchEvent(event);
    }

    public interface OnLetterListener {
        void move(int position, String s);

        void clickDown(int position, String s);

        void clickUp(int position);
    }


    public void setOnLetterListener(OnLetterListener listener) {
        this.listener = listener;
    }
}
