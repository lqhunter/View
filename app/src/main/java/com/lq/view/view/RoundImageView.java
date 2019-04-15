package com.lq.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * author : lqhunter
 * date : 2019/3/29 0029
 * description : 圆形头像
 */
public class RoundImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint mPaint;

    //边界圆圈宽度
    private int borderWidth = 10;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);不能加，当xml里设置src时，这个方法会先绘制一遍src的图片

        Drawable drawable = getDrawable();

        //空值判断，必要步骤，避免由于没有设置src导致的异常错误
        if (drawable == null) {
            return;
        }
        //必要步骤，避免由于初始化之前导致的异常错误
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        if (!(drawable instanceof BitmapDrawable)) {
            return;
        }


        Bitmap b = ((BitmapDrawable) drawable).getBitmap();

        if (null == b) {
            return;
        }

        //view的宽度作为最后生成图像的直径
        Bitmap roundBitmap = getCroppedBitmap(b, getWidth());
        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }

    /**
     * 初始Bitmap对象的缩放裁剪过程
     *
     * @param bmp    初始Bitmap对象
     * @param radius 圆形图片直径大小
     * @return 返回一个圆形的缩放裁剪过后的Bitmap对象
     */
    public Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        //比较初始Bitmap宽高和给定的圆形直径，判断是否需要缩放裁剪Bitmap对象
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            //将原图像缩放后生成正方形bitmap
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;


        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        //新建Canvas，Canvas相当于图层，通过Canvas改变output中的像素
        Canvas canvas = new Canvas(output);

        //裁减成圆图片
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setDither(true);
        //圆形作为Dst图片
        canvas.drawCircle(sbmp.getWidth() / 2,
                sbmp.getHeight() / 2, sbmp.getWidth() / 2, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//IN表示相交的部分，显示SRC内容
        Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        //sbmp作为src图片
        canvas.drawBitmap(sbmp, rect, rect, mPaint);


        //画外边界
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#BAB399"));//暗白色
        //paint.setColor(Color.parseColor("#FFFFFFFF"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(sbmp.getWidth() / 2,
                sbmp.getHeight() / 2, sbmp.getWidth() / 2 - borderWidth / 2, paint);

        return output;
    }
}
