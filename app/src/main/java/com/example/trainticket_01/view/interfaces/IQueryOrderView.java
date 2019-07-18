package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.OrderBean;

import java.util.List;

/**
 * Created by maomao on 2019/4/11.
 * <p>
 * 订单View接口
 */

public interface IQueryOrderView
{
    /**
     * 获取订单
     */
    void doQueryOrder();

    /**
     * 查询成功
     *
     * @param orders 订单列表
     */
    void querySuccess(List<OrderBean> orders);

    /**
     * 查询失败
     *
     * @param msg
     */
    void queryFailed(String msg);
}
