package com.lq.view.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
public class PopMenuFragment extends BaseFragment {

    private static final String TAG = "PopMenuFragment";
    private float popRadius = 400;
    private View[] mBtn;
    private boolean isPoped = false;


    @BindView(R.id.san)
    Button san;
    @BindView(R.id.fang)
    Button fang;
    @BindView(R.id.quan)
    Button quan;
    @BindView(R.id.cha)
    Button cha;
    @BindView(R.id.muti)
    Button muti;
    @BindView(R.id.main)
    Button main;

    private View mView;
    private Unbinder mBind;

    public static PopMenuFragment newInstance() {

        Bundle args = new Bundle();

        PopMenuFragment fragment = new PopMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.activity_pop_menu, container, false);
        mBind = ButterKnife.bind(this, mView);

        mBtn = new View[]{san, fang, quan, cha, muti};

        return mView;
    }

    @OnClick(R.id.san)
    public void onSanClicked() {
        Log.d(TAG, "onSanClicked...");
        closeMenu();

    }

    @OnClick(R.id.fang)
    public void onFangClicked() {
        Log.d(TAG, "onFangClicked...");
        closeMenu();

    }

    @OnClick(R.id.quan)
    public void onQuanClicked() {
        Log.d(TAG, "onQuanClicked...");
        closeMenu();

    }

    @OnClick(R.id.cha)
    public void onChaClicked() {
        Log.d(TAG, "onChaClicked...");
        closeMenu();


    }

    @OnClick(R.id.muti)
    public void onMutiClicked() {
        Log.d(TAG, "onMutiClicked...");
        closeMenu();

    }

    @OnClick(R.id.main)
    public void onMainClicked() {
        if (!isPoped) {
            popMenu2();
        } else {
            closeMenu();
        }

    }

    private void popMenu() {
        for (int i = 0; i < 5; i++) {
            ObjectAnimator mutiXTrans = ObjectAnimator.ofFloat(mBtn[i], "translationX",
                    0, (float) (-popRadius * Math.sin(Math.PI / 8 * i)));
            ObjectAnimator mutiYTrans = ObjectAnimator.ofFloat(mBtn[i], "translationY",
                    0, (float) (-popRadius * Math.cos(Math.PI / 8 * i)));
            ObjectAnimator mutiXscale = ObjectAnimator.ofFloat(mBtn[i], "scaleX", 0, 1);
            ObjectAnimator mutiYscale = ObjectAnimator.ofFloat(mBtn[i], "scaleY", 0, 1);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(mutiXTrans, mutiXscale, mutiYscale, mutiYTrans);
            set.start();
        }
        isPoped = true;
    }

    private void popMenu2() {
        for (int i = 0; i < 5; i++) {
            ObjectAnimator mutiXTrans = ObjectAnimator.ofFloat(mBtn[i], "translationX",
                    0, (float) (-popRadius * Math.sin(Math.PI / 8 * i)));
            ObjectAnimator mutiYTrans = ObjectAnimator.ofFloat(mBtn[i], "translationY",
                    0, (float) (-popRadius * Math.cos(Math.PI / 8 * i)));
            ObjectAnimator mutiXscale = ObjectAnimator.ofFloat(mBtn[i], "scaleX", 0, 1);
            ObjectAnimator mutiYscale = ObjectAnimator.ofFloat(mBtn[i], "scaleY", 0, 1);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(mutiXTrans, mutiXscale, mutiYscale, mutiYTrans);
            //每个btn延时出现动画
            set.setStartDelay(50 * i);
            set.start();
        }
        isPoped = true;
    }

    private void closeMenu() {
        for (int i = 0; i < 5; i++) {
            ObjectAnimator mutiXTrans = ObjectAnimator.ofFloat(mBtn[i], "translationX",
                    (float) (-popRadius * Math.sin(Math.PI / 8 * i)), 0);
            ObjectAnimator mutiYTrans = ObjectAnimator.ofFloat(mBtn[i], "translationY",
                    (float) (-popRadius * Math.cos(Math.PI / 8 * i)), 0);
            ObjectAnimator mutiXscale = ObjectAnimator.ofFloat(mBtn[i], "scaleX", 1, 0);
            ObjectAnimator mutiYscale = ObjectAnimator.ofFloat(mBtn[i], "scaleY", 1, 0);

            AnimatorSet set = new AnimatorSet();
            set.playTogether(mutiXTrans, mutiXscale, mutiYscale, mutiYTrans);
            set.start();
        }
        isPoped = false;

    }

}
