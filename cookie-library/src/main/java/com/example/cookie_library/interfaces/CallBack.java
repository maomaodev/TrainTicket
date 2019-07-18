package com.example.cookie_library.interfaces;

/**
 * Created by maomao on 2019/4/3.
 *
 * 网络请求回调接口
 */

public interface CallBack
{
    void onSuccess(Object result);
    void onFailed(Object msg);
}
