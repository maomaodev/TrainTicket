package com.example.cookie_library.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cookie_library.application.ActivityCollections;

import org.xutils.x;

/**
 * Created by maomao on 2019/4/2.
 * <p>
 * Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener
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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // 竖屏锁定
        ActivityCollections.getInstance().addActivity(this);
        x.view().inject(this);  // xUtils3的view注解
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

    /**
     * 跳转另一个活动
     *
     * @param clazz
     */
    protected void turn(Class<?> clazz)
    {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 跳转另一个活动并传递参数
     *
     * @param clazz
     * @param bundle
     */
    protected void turn(Class<?> clazz, Bundle bundle)
    {
        Intent intent = new Intent(this, clazz);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转另一个活动并结束当前
     *
     * @param clazz
     */
    protected void turnThenFinish(Class<?> clazz)
    {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转另一个活动并结束，并传递参数
     *
     * @param clazz
     * @param bundle
     */
    protected void turnThenFinish(Class<?> clazz, Bundle bundle)
    {
        Intent intent = new Intent(this, clazz);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    /**
     * 开始一个活动，并等待返回结果
     *
     * @param clazz
     * @param requestCode
     */
    protected void turnForResult(Class<?> clazz, int requestCode)
    {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 开始一个活动，并等待返回结果，并传递参数
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void turnForResult(Class<?> clazz, int requestCode, Bundle bundle)
    {
        Intent intent = new Intent(this, clazz);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }
}
