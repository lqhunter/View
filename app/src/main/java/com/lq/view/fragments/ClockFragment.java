package com.lq.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lq.view.R;
import com.lq.view.base.BaseFragment;

/**
 * author : lqhunter
 * date : 2019/4/14 0014
 * description :
 */
public class ClockFragment extends BaseFragment {

    private View mView;

    public static ClockFragment newInstance() {

        Bundle args = new Bundle();

        ClockFragment fragment = new ClockFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.fragment_clock, container, false);

        return mView;
    }
}
