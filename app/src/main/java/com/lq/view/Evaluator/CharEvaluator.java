package com.lq.view.Evaluator;

import android.animation.TypeEvaluator;

/**
 * author : lqhunter
 * date : 2019/4/9 0009
 * description : 字符
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        char c = (char) (startValue + (endValue - startValue) * fraction);
        return c;
    }
}
