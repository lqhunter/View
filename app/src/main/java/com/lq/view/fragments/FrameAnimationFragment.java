package com.lq.view.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class FrameAnimationFragment extends BaseFragment {

    private View mView;
    private Unbinder mBind;

    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.image)
    ImageView image;


    public static FrameAnimationFragment newInstance() {

        Bundle args = new Bundle();

        FrameAnimationFragment fragment = new FrameAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.activity_frame_animation, container, false);
        mBind = ButterKnife.bind(this, mView);

        image.setBackgroundResource(R.drawable.animation_list);
        return mView;
    }

    @OnClick(R.id.start)
    public void onStartClicked() {
        AnimationDrawable animationDrawable = (AnimationDrawable) image.getBackground();
        if (animationDrawable != null) {
            animationDrawable.start();
        }
    }

    @OnClick(R.id.stop)
    public void onStopClicked() {
        AnimationDrawable animationDrawable = (AnimationDrawable) image.getBackground();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }
}
