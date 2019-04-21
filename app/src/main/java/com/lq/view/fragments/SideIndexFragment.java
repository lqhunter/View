package com.lq.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lq.view.R;
import com.lq.view.base.BaseFragment;
import com.lq.view.view.SideIndexBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class SideIndexFragment extends BaseFragment {

    private static final String TAG = "SideIndexFragment";
    private View mView;
    private Unbinder mBind;
    private SideIndexBar mSideIndexBar;
    private TextView mSideIndexTV;

    public static SideIndexFragment newInstance() {

        Bundle args = new Bundle();

        SideIndexFragment fragment = new SideIndexFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.fragment_side_index, container, false);
        mBind = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        mSideIndexTV = mView.findViewById(R.id.side_index_tv);
        mSideIndexBar = mView.findViewById(R.id.side_index_bar);
        mSideIndexBar.setOnLetterListener(new SideIndexBar.OnLetterListener() {

            @Override
            public void clickDown(int position, String s) {
                Log.d(TAG, "按下的位置-->" + position);
                mSideIndexTV.setText(s);
                mSideIndexTV.setVisibility(View.VISIBLE);
            }

            @Override
            public void move(int position, String s) {
                Log.d(TAG, "移动的位置-->" + position);
                mSideIndexTV.setText(s);
            }

            @Override
            public void clickUp(int position) {
                Log.d(TAG, "抬起的位置-->" + position);
                mSideIndexTV.setVisibility(View.GONE);
            }
        });
    }
}
