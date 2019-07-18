package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.UserBean;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 登录页View接口
 */

public interface ILoginView
{
    /**
     * 登录
     */
    void doLogin();

    /**
     * 自动登录
     */
    void autoLogin();

    /**
     * 登录成功
     *
     * @param userBean
     */
    void loginSuccess(UserBean userBean);

    /**
     * 登录失败
     *
     * @param msg
     */
    void loginFailed(String msg);
}
