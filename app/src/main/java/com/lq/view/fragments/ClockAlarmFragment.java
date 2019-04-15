package com.lq.view.fragments;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

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
public class ClockAlarmFragment extends BaseFragment {

    @BindView(R.id.start_alarm)
    Button startAlarm;
    @BindView(R.id.clock)
    ImageView clock;

    private View mView;
    private Unbinder mBind;

    public static ClockAlarmFragment newInstance() {

        Bundle args = new Bundle();

        ClockAlarmFragment fragment = new ClockAlarmFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.activity_clock_alarm, container, false);
        mBind = ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.start_alarm)
    public void onStartAlarmClicked() {
//        实例多个PropertyValuesHolder完成多个动画效果
//        PropertyValuesHolder rotateHolder = PropertyValuesHolder.ofFloat("rotation",
//                0, 5, -5, 10, -10, 20, -20, 30, -30, 40, -40, 50, -50,
//                60f, -60f, 50f, -50f, 40f, -40f, 40f, -40f, 30f, -30f, 20f, -20f, 10f, -10f, 5f, -5f, 0);
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);

        PropertyValuesHolder rotateHolder = PropertyValuesHolder.ofKeyframe("rotation",
                frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);
        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f, 1f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.5f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(clock, rotateHolder, scaleXHolder, scaleYHolder);

        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
