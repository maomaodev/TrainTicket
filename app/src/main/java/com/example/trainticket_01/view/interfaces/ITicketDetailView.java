package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.OrderListBean;

/**
 * Created by maomao on 2019/4/9.
 *
 * 车票详情View接口
 */

public interface ITicketDetailView
{
    /**
     * 订票
     */
    void doOrderTicket();

    /**
     * 订票成功
     */
    void orderSuccess(OrderListBean orderListBean);

    /**
     * 订票失败
     * @param msg
     */
    void orderFailed(String msg);
}
