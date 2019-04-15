package com.lq.view.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * author : lqhunter
 * date : 2019/4/14 0014
 * description :
 */
public abstract class BaseFragment extends SupportFragment {

    private View mRootView;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        mRootView = onSubViewLoaded(inflater, container);
        return mRootView;
    }


    // activity创建结束,在该方法内可以进行与Activity交互的UI操作
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据, 子类可以不实现
     */
    public void initData() {

    }


    protected abstract View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container);
}
