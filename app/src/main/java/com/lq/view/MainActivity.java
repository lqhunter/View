package com.lq.view;

import android.os.Bundle;

import com.lq.view.fragments.MainFragment;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findFragment(MainFragment.class) == null) {
            //加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }
}
