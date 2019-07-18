package com.example.trainticket_01.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;

import com.example.cookie_library.activity.BaseActivity;
import com.example.trainticket_01.R;

import org.xutils.view.annotation.ContentView;

/**
 * Created by maomao on 2019/4/2.
 * <p>
 * 启动页
 */

@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity
{
    private final int SPLASH_DISPLAY_DURATION = 1500;   //启动页显示时长
    private Looper looper = Looper.myLooper();
    private Handler handler = new Handler(looper);

    private Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            turnThenFinish(LoginActivity.class);
        }
    };

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        handler.postDelayed(runnable, SPLASH_DISPLAY_DURATION);
    }

    @Override
    public void widgetClick(View view)
    {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)    // 启动时按下返回键
            handler.removeCallbacks(runnable);
        return super.onKeyDown(keyCode, event);
    }
}
