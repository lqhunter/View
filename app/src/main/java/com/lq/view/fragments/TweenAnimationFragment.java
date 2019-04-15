package com.lq.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
public class TweenAnimationFragment extends BaseFragment {

    @BindView(R.id.scale)
    Button scale;
    @BindView(R.id.rotate)
    Button rotate;
    @BindView(R.id.alpha)
    Button alpha;
    @BindView(R.id.translate)
    Button translate;
    @BindView(R.id.set)
    Button set;
    @BindView(R.id.image)
    ImageView image;

    private View mView;
    private Unbinder mBind;

    private Animation mAnimation;

    public static TweenAnimationFragment newInstance() {

        Bundle args = new Bundle();

        TweenAnimationFragment fragment = new TweenAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.fragment_tween_animation, container, false);
        mBind = ButterKnife.bind(this, mView);

        return mView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick({R.id.scale, R.id.rotate, R.id.alpha, R.id.translate, R.id.set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.scale:
                mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.test_scale);
                image.startAnimation(mAnimation);
                break;
            case R.id.rotate:
                mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.test_rotate);
                image.startAnimation(mAnimation);
                break;
            case R.id.alpha:
                mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.test_alpha);
                image.startAnimation(mAnimation);
                break;
            case R.id.translate:
                mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.test_translate);
                image.startAnimation(mAnimation);
                break;
            case R.id.set:
                mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.test_set);
                image.startAnimation(mAnimation);
                break;
        }
    }
}
