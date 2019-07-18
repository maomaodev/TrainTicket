package com.example.cookie_library.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.cookie_library.application.ActivityCollections;

import org.xutils.x;

/**
 * Created by maomao on 2019/4/2.
 * <p>
 * FragmentActivity基类
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener
{
    /**
     * 初始化控件和事件
     *
     * @param savedInstanceState
     */
    public abstract void initWidget(Bundle savedInstanceState);

    /**
     * 控件的监听事件
     *
     * @param view 控件
     */
    public abstract void widgetClick(View view);

    @Override
    public void onClick(View v)
    {
        widgetClick(v);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // 竖屏锁定
        ActivityCollections.getInstance().addActivity(this);
        x.view().inject(this);  //xUtils3的view注解
        initWidget(savedInstanceState);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        ActivityCollections.getInstance().finishActivity(this);
    }
}
