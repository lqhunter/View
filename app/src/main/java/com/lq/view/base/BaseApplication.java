package com.lq.view.base;

import android.app.Application;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * author : lqhunter
 * date : 2019/4/14 0014
 * description :
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 栈视图等功能，建议在Application里初始化
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                .install();
    }
}
