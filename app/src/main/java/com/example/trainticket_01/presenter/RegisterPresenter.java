package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.model.biz.RegisterBiz;
import com.example.trainticket_01.view.interfaces.IRegisterView;

/**
 * Created by maomao on 2019/4/4.
 * <p>
 * 注册Presenter
 */

public class RegisterPresenter
{
    private RegisterBiz registerBiz;
    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView)
    {
        registerBiz = new RegisterBiz();
        this.iRegisterView = iRegisterView;
    }

    public void doRegister(UserBean userBean)
    {
        registerBiz.doRegister(userBean, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iRegisterView.registerSuccess((String) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iRegisterView.registerFailed((String) msg);
            }
        });
    }
}
