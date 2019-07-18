package com.example.trainticket_01.view.interfaces;

import com.example.trainticket_01.model.bean.TicketListBean;

/**
 * Created by maomao on 2019/4/7.
 * <p>
 * 车票查询View接口
 */

public interface IQueryTicketView
{
    /**
     * 车票查询
     *
     * @param from  出发站点
     * @param to    目的站点
     * @param date  出发时间
     * @param model 火车类型
     */
    void doQueryTicket(String from, String to, String date, String model);

    /**
     * 查询成功
     *
     * @param ticketListBean 车票列表实体类
     */
    void querySuccess(TicketListBean ticketListBean);

    /**
     * 查询失败
     */
    void queryFailed();
}
