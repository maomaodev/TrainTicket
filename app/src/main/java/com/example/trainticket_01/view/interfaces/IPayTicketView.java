package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.OrderListBean;

/**
 * Created by maomao on 2019/4/10.
 * <p>
 * 支付车票View接口
 */

public interface IPayTicketView
{
    /**
     * 提交
     */
    void doPayTicket();

    /**
     * 支付成功
     *
     * @param orderListBean 订单列表实体类
     */
    void paySuccess(OrderListBean orderListBean);

    /**
     * 支付失败
     *
     * @param msg
     */
    void payFailed(String msg);
}
