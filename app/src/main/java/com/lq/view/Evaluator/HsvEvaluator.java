package com.lq.view.Evaluator;

import android.animation.TypeEvaluator;
import android.graphics.Color;

/**
 * author : lqhunter
 * date : 2019/4/9 0009
 * description : HSV 求值器
 * HSV模型 就是按 色彩、深浅、明暗 来描述的颜色
 */
public class HsvEvaluator implements TypeEvaluator<Integer> {
    private float[] startHsv = new float[3];
    private float[] endHsv = new float[3];

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        //1.将开始和结束的ARGB值转换为HSV
        Color.colorToHSV(startValue, startHsv);
        Color.colorToHSV(endValue, endHsv);

        //2.计算当前动画完成度对应的HSV颜色值和透明度
        float[] outHsv = new float[3];
        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;
        int alpha = (int) (Color.alpha(startValue) + (Color.alpha(endValue) - Color.alpha(startValue)) * fraction);

        //3.将HSV转换成ARGB值返回
        return Color.HSVToColor(alpha, outHsv);
    }
}
