package com.example.cookie_library.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * Application基类
 */

public class BaseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        // 全局对象xUtils初始化
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
