package com.example.trainticket_01.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.cookie_library.application.ActivityCollections;
import com.example.cookie_library.utils.ToastUtils;
import com.example.trainticket_01.R;
import com.example.trainticket_01.view.fragment.MeFragment;
import com.example.trainticket_01.view.fragment.OrderFragment;
import com.example.trainticket_01.view.fragment.TicketFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 主页
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity
{
    private long exitTime;

    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.main_content)
    private ViewPager mainContent;
    @ViewInject(R.id.main_tab)
    private SpaceTabLayout mainTab;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        // 设置标题
        titleTv.setText(R.string.app_name);
        // 设置内容
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new TicketFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new MeFragment());
        // 关联内容和导航栏
        mainTab.initialize(mainContent, getSupportFragmentManager(), fragmentList,
                savedInstanceState);
        mainContent.setOffscreenPageLimit(3);
    }

    @Override
    public void widgetClick(View view)
    {
    }

    /**
     * 点两次返回退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if ((System.currentTimeMillis() - exitTime) > 2000)
            {
                ToastUtils.show(getApplicationContext(), R.string.exit_tip);
                exitTime = System.currentTimeMillis();
            }
            else
                ActivityCollections.getInstance().finishAllActivity();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
