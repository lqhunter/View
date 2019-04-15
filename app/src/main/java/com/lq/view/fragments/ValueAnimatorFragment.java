package com.lq.view.fragments;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lq.view.Evaluator.CharEvaluator;
import com.lq.view.R;
import com.lq.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class ValueAnimatorFragment extends BaseFragment {

    private View mView;
    private Unbinder mBind;

    @BindView(R.id.start_animation)
    Button startAnimation;
    @BindView(R.id.btn_demo)
    Button btnDemo;
    @BindView(R.id.cancel_animation)
    Button cancelAnimation;
    @BindView(R.id.pause_animation)
    Button pauseAnimation;
    @BindView(R.id.resume_animation)
    Button resumeAnimation;
    @BindView(R.id.btn_text_animation)
    Button btnTextAnimation;
    @BindView(R.id.textView)
    TextView textView;

    private ValueAnimator mValueAnimator;

    public static ValueAnimatorFragment newInstance() {

        Bundle args = new Bundle();

        ValueAnimatorFragment fragment = new ValueAnimatorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.activity_value_animator, container, false);
        mBind = ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.start_animation)
    public void onStartAnimationClicked() {
        mValueAnimator = ValueAnimator.ofInt(0, 800);

        mValueAnimator.setDuration(3000);
        //设置弹跳插值器
        mValueAnimator.setInterpolator(new BounceInterpolator());
        new LinearInterpolator();
        new LinearInterpolator();
        //设置重复模式为反转
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //设置无限次
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                btnDemo.setX(value);
            }
        });

        mValueAnimator.start();
    }

    @OnClick(R.id.btn_demo)
    public void onBtnDemoClicked() {
        Toast.makeText(getContext(), "点击。。", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.cancel_animation)
    public void onCancelClicked() {
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
        }
    }

    @OnClick(R.id.pause_animation)
    public void onPauseAnimationClicked() {
        if (mValueAnimator != null) {
            mValueAnimator.pause();
        }
    }

    @OnClick(R.id.resume_animation)
    public void onResumeAnimationClicked() {
        if (mValueAnimator != null) {
            mValueAnimator.resume();
        }
    }

    @OnClick(R.id.btn_text_animation)
    public void onTextAnimClicked() {
        //从A 变化到 Z，通过CharEvaluator计算中间值
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), 'A', 'Z');
        //设置减速插值器
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        //添加变化的监听器
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char c = (char) animation.getAnimatedValue();
                textView.setText(String.valueOf(c));
            }
        });
        valueAnimator.setDuration(5000);

        valueAnimator.start();
    }
}
