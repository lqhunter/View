package com.lq.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lq.view.R;
import com.lq.view.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class WaterWaveFragment extends BaseFragment {

    private View mView;
    private Unbinder mBind;

    public static WaterWaveFragment newInstance() {

        Bundle args = new Bundle();

        WaterWaveFragment fragment = new WaterWaveFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.fragment_water_wave, container, false);
        mBind = ButterKnife.bind(this, mView);

        return mView;
    }
}
