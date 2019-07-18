package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.model.biz.ModifyPasswordBiz;
import com.example.trainticket_01.view.interfaces.IModifyPasswordView;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 修改密码Presenter
 */

public class ModifyPasswordPresenter
{
    private ModifyPasswordBiz modifyPasswordBiz;
    private IModifyPasswordView iModifyPasswordView;

    public ModifyPasswordPresenter(IModifyPasswordView iModifyPasswordView)
    {
        modifyPasswordBiz = new ModifyPasswordBiz();
        this.iModifyPasswordView = iModifyPasswordView;
    }

    public void doModifyPassword(final UserBean userBean, String newPassword)
    {
        modifyPasswordBiz.doModifyPassword(userBean, newPassword, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iModifyPasswordView.modifySuccess((UserBean) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iModifyPasswordView.modifyFailed((String) msg);
            }
        });
    }

}
