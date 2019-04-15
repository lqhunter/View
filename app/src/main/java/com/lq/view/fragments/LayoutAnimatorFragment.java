package com.lq.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.lq.view.R;
import com.lq.view.adapters.MyAdapter;
import com.lq.view.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : lqhunter
 * date : 2019/4/15 0015
 * description :
 */
public class LayoutAnimatorFragment extends BaseFragment implements MyAdapter.OnItemClickListener {

    private static final String TAG = "LayoutAnimatorFragment";
    private View mView;
    private Unbinder mBind;

    private RecyclerView mRlv;


    public static LayoutAnimatorFragment newInstance() {

        Bundle args = new Bundle();

        LayoutAnimatorFragment fragment = new LayoutAnimatorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View onSubViewLoaded(LayoutInflater layoutInflater, ViewGroup container) {
        mView = layoutInflater.inflate(R.layout.activity_layout_animation, container, false);
        mBind = ButterKnife.bind(this, mView);

        initView();

        return mView;
    }

    private void initView() {
        mRlv = mView.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlv.setLayoutManager(manager);

        MyAdapter adapter = new MyAdapter(getContext());
        adapter.setOnItemClickListener(this);
        mRlv.setAdapter(adapter);

        test();
    }

    @Override
    public void onClick(int position) {
        Log.d(TAG, "点击了..." + position);

    }

    public void test() {

        //加载单个文件的animation
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.item_in_set);
        //构造ViewGroup的LayoutAnimation
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        //设置item间显示间隔
        controller.setDelay(0.2f);
        //设置显示顺序
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //为 recyclerView 设置LayoutAnimationController
        mRlv.setLayoutAnimation(controller);
        //开始动画
        mRlv.startLayoutAnimation();

    }
}
