package com.lq.view.fragments;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
public class AnimatorSetFragment extends BaseFragment {
    private static final String TAG = "AnimatorSetFragment";
    private View mView;
    private Unbinder mBind;

    @BindView(R.id.playSequentially)
    Button playSequentially;
    @BindView(R.id.playTogether)
    Button playTogether;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    private AnimatorSet mAnimatorSet;

    public static AnimatorSetFragment newInstance() {

        Bundle args = new Bundle();

        AnimatorSetFragment fragment = new AnimatorSetFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.activity_animator_set, container, false);
        mBind = ButterKnife.bind(this, mView);
        return mView;
    }

    @OnClick(R.id.playSequentially)
    public void onPlaySequentiallyClicked() {
        ObjectAnimator text1BgColor = ObjectAnimator.ofInt(text1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator text1TransY = ObjectAnimator.ofFloat(text1, "translationY", 0, 300f, 0);
        ObjectAnimator text2TransY = ObjectAnimator.ofFloat(text2, "translationY", 0, 300f, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(text1BgColor, text1TransY, text2TransY);
        //此处是对每一个都设置1秒
        animatorSet.setDuration(1000);
        animatorSet.start();

    }

    @OnClick(R.id.playTogether)
    public void onPlayTogetherClicked() {
        ObjectAnimator text1BgColor = ObjectAnimator.ofInt(text1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator text1TransY = ObjectAnimator.ofFloat(text1, "translationY", 0, 300f, 0);
        ObjectAnimator text2TransY = ObjectAnimator.ofFloat(text2, "translationY", 0, 300f, 0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(text1BgColor, text1TransY, text2TransY);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    @OnClick(R.id.builder)
    public void onBuilderClicked() {
        ObjectAnimator text1BgColor = ObjectAnimator.ofInt(text1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator text1TransY = ObjectAnimator.ofFloat(text1, "translationY", 0, 300f, 0);
        ObjectAnimator text2TransY = ObjectAnimator.ofFloat(text2, "translationY", 0, 300f, 0);


        mAnimatorSet = new AnimatorSet();

        /*mAnimatorSet.playSequentially(text1BgColor, text1TransY);//让A,B顺序播放
        //创建一个builder类, 能建立text1TransY动画相依赖（联系）的其他动画的关系
        AnimatorSet.Builder play = mAnimatorSet.play(text1TransY);
        play.with(text2TransY);//B的播放伴随着C同时播放*/

        mAnimatorSet.play(text1TransY).with(text2TransY).after(text1BgColor);

        mAnimatorSet.setDuration(1000);

        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart...");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd...");

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel...");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat...");

            }
        });


        mAnimatorSet.start();

    }

    @OnClick(R.id.cancel_animator)
    public void onCancelClicked() {
        if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
            mAnimatorSet.cancel();
        }
    }

    public void test() {
        ValueAnimator valueAnimator = (ValueAnimator)AnimatorInflater.loadAnimator(getContext(), R.animator.animator);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //TODO:更新控件属性值
            }
        });
        valueAnimator.start();

    }

    public void test1() {
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), R.animator.object_animator);
        objectAnimator.setTarget(text1);
        objectAnimator.start();
    }
}
