package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.UserBean;

/**
 * Created by maomao on 2019/4/6.
 * <p>
 * 修改信息View接口
 */

public interface IModifyInfoView
{
    /**
     * 修改信息
     */
    void doModifyInfo();

    /**
     * 修改成功
     *
     * @param userBean
     */
    void modifySuccess(UserBean userBean);

    /**
     * 修改失败
     *
     * @param msg
     */
    void modifyFailed(String msg);
}
