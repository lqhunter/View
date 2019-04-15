package com.lq.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lq.view.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * author : lqhunter
 * date : 2019/4/14 0014
 * description :
 */
public class MainFragment extends SupportFragment {

    private static final String TAG = "MainFragment";

    @BindView(R.id.test)
    Button test;
    @BindView(R.id.clock)
    Button clock;
    @BindView(R.id.finger_draw)
    Button fingerDraw;
    @BindView(R.id.water_wave)
    Button waterWave;
    @BindView(R.id.round_image)
    Button roundImage;
    @BindView(R.id.bottom_bar)
    Button bottomBar;
    @BindView(R.id.guaguale)
    Button guaguale;
    @BindView(R.id.tween_animation)
    Button tweenAnimation;
    @BindView(R.id.frame_animation)
    Button frameAnimation;
    @BindView(R.id.value_animator)
    Button valueAnimator;
    @BindView(R.id.evaluator)
    Button evaluator;
    @BindView(R.id.clock_alarm)
    Button clockAlarm;
    @BindView(R.id.animator_set)
    Button animatorSet;
    @BindView(R.id.pop_menu)
    Button popMenu;
    @BindView(R.id.layout_animation)
    Button layoutAnimation;
    @BindView(R.id.view_pager_indicator)
    Button viewPagerIndicator;
    private View mView;
    private Unbinder mBind;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mBind = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick(R.id.test)
    public void onTestClicked() {

    }

    @OnClick(R.id.clock)
    public void onClockClicked() {
        Log.d(TAG, "onClockClicked...");
        start(ClockFragment.newInstance());
    }

    @OnClick(R.id.finger_draw)
    public void onFingerDrawClicked() {
        start(FingerDrawFragment.newInstance());

    }

    @OnClick(R.id.water_wave)
    public void onWaterWaveClicked() {
        start(WaterWaveFragment.newInstance());
    }

    @OnClick(R.id.round_image)
    public void onRoundImageClicked() {
        start(RoundImageFragment.newInstance());
    }

    @OnClick(R.id.bottom_bar)
    public void onBottomBarClicked() {
        start(BottomBarFragment.newInstance());

    }

    @OnClick(R.id.guaguale)
    public void onGuagualeClicked() {
        start(GuaGualeFragment.newInstance());
    }

    @OnClick(R.id.tween_animation)
    public void onTweenAnimationClicked() {
        start(TweenAnimationFragment.newInstance());

    }

    @OnClick(R.id.frame_animation)
    public void onFrameAnimationClicked() {
        start(FrameAnimationFragment.newInstance());

    }

    @OnClick(R.id.value_animator)
    public void onValueAnimatorClicked() {
        start(ValueAnimatorFragment.newInstance());

    }

    @OnClick(R.id.evaluator)
    public void onEvaluatorClicked() {
        start(EvaluatorFragment.newInstance());
    }

    @OnClick(R.id.clock_alarm)
    public void onClockAlarmClicked() {
        start(ClockAlarmFragment.newInstance());

    }

    @OnClick(R.id.animator_set)
    public void onAnimatorSetClicked() {
        start(AnimatorSetFragment.newInstance());

    }

    @OnClick(R.id.pop_menu)
    public void onPopMenuClicked() {
        start(PopMenuFragment.newInstance());

    }

    @OnClick(R.id.layout_animation)
    public void onLayoutAnimationClicked() {
        start(LayoutAnimatorFragment.newInstance());

    }

    @OnClick(R.id.view_pager_indicator)
    public void onViewPagerIndicatorClicked() {
        start(IndicatorFragment.newInstance());

    }
}
