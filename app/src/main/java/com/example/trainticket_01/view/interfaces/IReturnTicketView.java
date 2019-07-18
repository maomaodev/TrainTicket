package com.example.trainticket_01.view.interfaces;

/**
 * Created by maomao on 2019/4/11.
 * <p>
 * 退票页View接口
 */

public interface IReturnTicketView
{
    /**
     * 退票
     */
    void doReturnTicket();

    /**
     * 退票成功
     *
     * @param msg
     */
    void returnSuccess(String msg);

    /**
     * 退票失败
     *
     * @param msg
     */
    void returnFailed(String msg);
}
