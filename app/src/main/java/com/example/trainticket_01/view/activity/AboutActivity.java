package com.example.trainticket_01.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookie_library.activity.BaseActivity;
import com.example.trainticket_01.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by maomao on 2019/4/5.
 *
 * 关于页
 */

@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity
{
    @ViewInject(R.id.title_tv)
    private TextView titleTv;
    @ViewInject(R.id.left_btn)
    private ImageView leftBtn;
    @ViewInject(R.id.version_tv)
    private TextView versionTv;

    @Override
    public void initWidget(Bundle savedInstanceState)
    {
        titleTv.setText(R.string.about_text);
        leftBtn.setImageResource(R.drawable.ic_back_white);
        leftBtn.setOnClickListener(this);
        versionTv.setText(R.string.version_text);
    }

    @Override
    public void widgetClick(View view)
    {
        switch (view.getId())
        {
            case R.id.left_btn:
                finish();
                break;
            default:
                break;
        }
    }
}
