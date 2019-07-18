package com.example.cookie_library.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

/**
 * Created by maomao on 2019/4/4.
 * <p>
 * Fragment基类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener
{
    private boolean isInjected = false;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        isInjected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        if (!isInjected) // xUtils3的view注解
            x.view().inject(this, this.getView());
        initWidget(savedInstanceState);
    }

    /**
     * 跳转另一个活动
     *
     * @param clazz
     */
    protected void turn(Class<?> clazz)
    {
        Intent intent = new Intent(getActivity(), clazz);
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
        Intent intent = new Intent(getActivity(), clazz);
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
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * 开始一个活动，并等待返回结果
     *
     * @param clazz
     * @param requestCode
     */
    protected void turnForResult(Class<?> clazz, int requestCode)
    {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }
}
