package com.lq.view.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lq.view.Evaluator.HsvEvaluator;
import com.lq.view.R;
import com.lq.view.base.BaseFragment;
import com.lq.view.view.GradientColor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class EvaluatorFragment extends BaseFragment {
    private View mView;
    private Unbinder mBind;

    @BindView(R.id.gradient_view)
    GradientColor gradientView;
    @BindView(R.id.btn_int)
    Button btnInt;
    @BindView(R.id.btn_hsv)
    Button btnHsv;
    @BindView(R.id.btn_argb)
    Button btnArgb;

    public static EvaluatorFragment newInstance() {

        Bundle args = new Bundle();

        EvaluatorFragment fragment = new EvaluatorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.fragment_evaluator, container, false);
        mBind = ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.btn_int)
    public void onBtnIntClicked() {
        //使用 ObjectAnimator 实现动画,必须 gradientView 内提供set, get方法
        ObjectAnimator animator = ObjectAnimator.ofInt(gradientView, "color", Color.RED, Color.GREEN);
        animator.setDuration(5000);
        animator.start();
    }

    @OnClick(R.id.btn_hsv)
    public void onBtnHsvClicked() {
        //使用 ValueAnimator 实现动画
        ValueAnimator valueAnimator = ValueAnimator.ofInt(Color.RED, Color.GREEN);
        valueAnimator.setEvaluator(new HsvEvaluator());
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                gradientView.setColor(color);
            }
        });
        valueAnimator.start();

    }

    @OnClick(R.id.btn_argb)
    public void onArgbClicked() {
        ObjectAnimator animator = ObjectAnimator.ofInt(gradientView, "color", Color.RED, Color.GREEN);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setDuration(5000);
        animator.start();
    }
}
