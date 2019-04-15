package com.lq.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lq.view.R;


/**
 * author : lqhunter
 * date : 2019/4/3 0003
 * description : 刮刮乐
 */
public class GuaGuaLe extends View {

    Paint mPaint;
    Path mPath;

    Bitmap mBmpSrc;
    Bitmap mBmpDst;
    private Canvas mOperateCanvas;
    private PorterDuffXfermode mPorterDuffXfermode;

    public GuaGuaLe(Context context) {
        this(context, null);
    }

    public GuaGuaLe(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GuaGuaLe(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStrokeWidth(60);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();


        mBmpSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        mBmpDst = Bitmap.createBitmap(mBmpSrc.getWidth(), mBmpSrc.getHeight(), Bitmap.Config.ARGB_8888);

        //canvas的操作对象为mBmpDst
        mOperateCanvas = new Canvas(mBmpDst);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        //修改划过的path到Dst图像上
        mOperateCanvas.drawPath(mPath, mPaint);

        //以path为Dst，背景图为Src,SRC_IN模式，将显示重叠的SRC部分
        mPaint.setXfermode(mPorterDuffXfermode);
        mOperateCanvas.drawBitmap(mBmpSrc, 0, 0, mPaint);//canvas通过mpaint将mBmpSrc绘制到mBmpDst中
        mPaint.setXfermode(null);

        //然后通过主canvas把目标图像mBmpDst画到最终显示在屏幕的bitmap上
        canvas.drawBitmap(mBmpDst, 0, 0, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }
}
