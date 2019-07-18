package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.UserBean;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 修改密码View接口
 */

public interface IModifyPasswordView
{
    /**
     * 修改密码
     */
    void doModifyPassword();

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
