package com.lq.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lq.view.R;
import com.lq.view.base.BaseFragment;
import com.lq.view.view.FingerDraw;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class FingerDrawFragment extends BaseFragment {

    @BindView(R.id.reset)
    Button reset;

    @BindView(R.id.finger_view)
    FingerDraw mFingerDraw;

    private Unbinder mBind;

    public static FingerDrawFragment newInstance() {

        Bundle args = new Bundle();

        FingerDrawFragment fragment = new FingerDrawFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        View view = layoutInflater.inflate(R.layout.fragment_finger_draw, container, false);
        mBind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @OnClick(R.id.reset)
    public void onViewClicked() {
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFingerDraw.reset();
            }
        });
    }
}
