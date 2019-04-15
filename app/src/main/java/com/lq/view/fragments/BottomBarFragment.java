package com.lq.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lq.view.R;
import com.lq.view.base.BaseFragment;
import com.lq.view.view.BottomBar.BottomBar;
import com.lq.view.view.BottomBar.BottomBarTab;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class BottomBarFragment extends BaseFragment {

    private static final String TAG = "BottomBarFragment";
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    Unbinder unbinder;
    private View mView;
    private Unbinder mBind;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    public static BottomBarFragment newInstance() {

        Bundle args = new Bundle();

        BottomBarFragment fragment = new BottomBarFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.fragment_bottom_tab, container, false);
        mBind = ButterKnife.bind(this, mView);

        initView();

        return mView;
    }


    private void initView() {
        bottomBar.addItem(new BottomBarTab(getContext(), R.mipmap.ic_message_white_24dp, "msg"));
        bottomBar.addItem(new BottomBarTab(getContext(), R.mipmap.ic_account_circle_white_24dp, "discover"));
        bottomBar.addItem(new BottomBarTab(getContext(), R.mipmap.ic_discover_white_24dp, "more"));

        // 模拟未读消息
        bottomBar.getItem(FIRST).setUnreadCount(9);

        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                Log.d(TAG, "onTabReselected..." + position);
            }

            @Override
            public void onTabUnselected(int position) {
                Log.d(TAG, "onTabUnselected..." + position);
            }

            @Override
            public void onTabReselected(int position) {
                Log.d(TAG, "onTabReselected..." + position);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }
}
