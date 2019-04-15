package com.lq.view.view.ViewPagerIndicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.lq.view.R;


/**
 * author : lqhunter
 * date : 2019/4/13 0013
 * description : 绘制分割线两边不同颜色的textview
 */
public class ViewPagerIndicatorTab extends android.support.v7.widget.AppCompatTextView {


    private Paint mOriginPaint;
    private int mOriginColor;

    private Paint mChangePaint;
    private int mChangeColor;
    private Rect mBound;

    public float getProgress() {
        return mProgress;
    }

    //滑动的百分比
    private float mProgress;
    //默认字体大小
    private static int mDefaultTextSize = 15;
    //滑动方向
    private Direction mCurrentDirection;
    //是否显示底部线条
    private boolean isShowBottomLine = true;
    //是否被选中
    private boolean isSelected = false;

    public enum Direction {
        RED_BLACK, BLACK_RED
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setCurrentDirection(Direction currentDirection) {
        mCurrentDirection = currentDirection;
    }

    public void setOriginColor(int originColor) {
        mOriginColor = originColor;
    }

    public void setChangeColor(int changeColor) {
        mChangeColor = changeColor;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

    public ViewPagerIndicatorTab(Context context, String text) {
        this(context, null, text, mDefaultTextSize);
    }


    public ViewPagerIndicatorTab(Context context, String text, int textSize) {
        this(context, null, text, textSize);
    }

    public ViewPagerIndicatorTab(Context context, AttributeSet attrs, String text, int textSize) {
        this(context, attrs, 0, text, textSize);
    }

    public ViewPagerIndicatorTab(Context context, AttributeSet attrs, int defStyleAttr, String text, int textSize) {
        super(context, attrs, defStyleAttr);


        setText(text);
        setTextSize(textSize);

        //得到自定义颜色属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicatorTab);
        mOriginColor = typedArray.getColor(R.styleable.ViewPagerIndicatorTab_originColor, Color.BLACK);
        mChangeColor = typedArray.getColor(R.styleable.ViewPagerIndicatorTab_changeColor, Color.RED);
        typedArray.recycle();

        initPaint();

        mBound = new Rect();


    }

    private void initPaint() {
        mOriginPaint = new Paint();
        mOriginPaint.setAntiAlias(true);
        mOriginPaint.setDither(true);
        mOriginPaint.setTextSize(getTextSize());

        mChangePaint = new Paint();
        mChangePaint.setAntiAlias(true);
        mChangePaint.setDither(true);
        mChangePaint.setTextSize(getTextSize());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mOriginPaint.setColor(mOriginColor);
        mChangePaint.setColor(mChangeColor);

        String text = getText().toString();
        mOriginPaint.getTextBounds(text, 0, text.length(), mBound);
        Paint.FontMetricsInt metricsInt = mOriginPaint.getFontMetricsInt();
        int dy = (metricsInt.bottom - metricsInt.top) / 2 - metricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;

        if (mCurrentDirection == Direction.BLACK_RED) {
            BlackDivRed(canvas, text, mBound, baseLine);
        } else {
            RedDivBlack(canvas, text, mBound, baseLine);
        }

    }

    /**
     * 绘制左红右黑(左 changeColor，右 originalColor)
     *
     * @param canvas
     * @param text
     * @param bound
     * @param baseLine
     */
    private void RedDivBlack(Canvas canvas, String text, Rect bound, int baseLine) {
        //画左边原始部分text
        canvas.save();
//        canvas.clipRect(0, 0, mProgress * getWidth(), getHeight());//剪切画布的百分比
        //剪切字体的百分比
        canvas.clipRect(0, 0, getWidth() / 2 - bound.width() / 2 + mProgress * bound.width(), getHeight());

        //canvas.drawText(text,getWidth() / 2 - bound.width() / 2, getHeight() /2 + (bound.height() / 2), mChangePaint);
        canvas.drawText(text, getWidth() / 2 - bound.width() / 2, baseLine, mChangePaint);
        canvas.restore();

        canvas.save();

        //画右面变化的text
        canvas.clipRect(getWidth() / 2 - bound.width() / 2 + mProgress * bound.width(), 0, getWidth(), getHeight());
        //canvas.drawText(text,getWidth() / 2 - bound.width() / 2, getHeight() /2 + (bound.height() / 2), mOriginPaint);
        canvas.drawText(text, getWidth() / 2 - bound.width() / 2, baseLine, mOriginPaint);
        canvas.restore();

        if (isShowBottomLine) {
            //画底部线条
            mChangePaint.setStrokeWidth(6);
            canvas.drawLine(0, getHeight(), getWidth() * mProgress, getHeight(), mChangePaint);
        }


    }

    /**
     * 绘制左黑右红(左 originalColor，右 changeColor)
     *
     * @param canvas
     * @param text
     * @param bound
     * @param baseLine
     */
    private void BlackDivRed(Canvas canvas, String text, Rect bound, int baseLine) {
        //画左边原始部分text
        canvas.save();
//        canvas.clipRect(0, 0, (1 - mProgress)* getWidth(), getHeight());

        canvas.clipRect(0, 0, getWidth() / 2 - bound.width() / 2 + (1 - mProgress) * bound.width() + 2, getHeight());

        //canvas.drawText(text,getWidth() / 2 - bound.width() / 2, getHeight() /2 + (bound.height() / 2), mChangePaint);
        canvas.drawText(text, getWidth() / 2 - bound.width() / 2, baseLine, mOriginPaint);
        canvas.restore();

        //画右边变化部分的text
        canvas.save();
        //加2个像素原因:当 mProgress = 0时,剪切的红色字体部分会从字体text的最后一个像素剪切,然后绘制text时会画一个像素红色，导致黑色不完整
        canvas.clipRect(getWidth() / 2 - bound.width() / 2 + (1 - mProgress) * bound.width() + 2, 0, getWidth(), getHeight());
        //canvas.drawText(text,getWidth() / 2 - bound.width() / 2, getHeight() /2 + (bound.height() / 2), mOriginPaint);
        canvas.drawText(text, getWidth() / 2 - bound.width() / 2, baseLine, mChangePaint);
        canvas.restore();


        if (isShowBottomLine) {
            //画底部线条
            mChangePaint.setStrokeWidth(6);
            canvas.drawLine(getWidth() * (1 - mProgress), getHeight(), getWidth(), getHeight(), mChangePaint);

        }
    }

}
