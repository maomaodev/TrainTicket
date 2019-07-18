package com.example.trainticket_01.config;

import com.example.cookie_library.application.BaseApplication;
import com.example.trainticket_01.model.bean.UserBean;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 程序的自定义Application类
 */

public class TrainTicketApplication extends BaseApplication
{
    public static UserBean user;

    public static void setUser(UserBean user)
    {
        TrainTicketApplication.user = user;
    }

    public static UserBean getUser()
    {
        return user;
    }
}
