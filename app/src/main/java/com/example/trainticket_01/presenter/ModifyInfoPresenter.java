package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.model.biz.ModifyInfoBiz;
import com.example.trainticket_01.view.interfaces.IModifyInfoView;

/**
 * Created by maomao on 2019/4/6.
 * <p>
 * 修改信息Presenter
 */

public class ModifyInfoPresenter
{
    private ModifyInfoBiz modifyInfoBiz;
    private IModifyInfoView iModifyInfoView;

    public ModifyInfoPresenter(IModifyInfoView iModifyInfoView)
    {
        modifyInfoBiz = new ModifyInfoBiz();
        this.iModifyInfoView = iModifyInfoView;
    }

    public void doModifyInfo(UserBean userBean)
    {
        modifyInfoBiz.doModifyInfo(userBean, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iModifyInfoView.modifySuccess((UserBean) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iModifyInfoView.modifyFailed((String) msg);
            }
        });
    }
}
