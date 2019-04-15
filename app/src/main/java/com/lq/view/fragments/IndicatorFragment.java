package com.lq.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lq.view.R;
import com.lq.view.base.BaseFragment;
import com.lq.view.view.ViewPagerIndicator.ViewPagerIndicator;
import com.lq.view.view.ViewPagerIndicator.ViewPagerIndicatorTab;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class IndicatorFragment extends BaseFragment {
    private static final String TAG = "IndicatorFragment";
    private View mView;
    private Unbinder mBind;

    private ViewPagerIndicator indicator;
    private String[] item = {"直播", "推荐", "视频", "图片", "段子","福利"};

    public static IndicatorFragment newInstance() {

        Bundle args = new Bundle();

        IndicatorFragment fragment = new IndicatorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.activity_indicator, container, false);
        mBind = ButterKnife.bind(this, mView);

        initIndicator();

        initViewPager();

        return mView;
    }


    private void initViewPager() {
        ViewPager viewPager = mView.findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
                                 @Override
                                 public Fragment getItem(int i) {
                                     return ViewPagerItemFragment.newIntence(item[i]);
                                 }

                                 @Override
                                 public int getCount() {
                                     return item.length;

                                 }
                             }
        );


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d(TAG, "position-->" + i + "  偏移百分比-->" + v + "  偏移像素-->" + i1);
                indicator.onPageScrolled(i, v);
            }

            @Override
            public void onPageSelected(int i) {
                indicator.setCurrentSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initIndicator() {
        indicator = mView.findViewById(R.id.indicator);

        for (int i = 0; i < item.length; i++) {

            ViewPagerIndicatorTab textView = new ViewPagerIndicatorTab(getContext(), item[i]);

            indicator.addMyView(textView);

        }
    }

}
