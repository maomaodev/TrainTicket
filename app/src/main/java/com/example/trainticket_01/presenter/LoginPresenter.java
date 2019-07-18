package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.model.biz.LoginBiz;
import com.example.trainticket_01.view.interfaces.ILoginView;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 登录Presenter
 */

public class LoginPresenter
{
    private LoginBiz loginBiz;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView)
    {
        loginBiz = new LoginBiz();
        this.iLoginView = iLoginView;
    }

    public void doLogin(final UserBean userBean)
    {
        loginBiz.doLogin(userBean, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iLoginView.loginSuccess((UserBean) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iLoginView.loginFailed((String) msg);
            }
        });
    }
}
