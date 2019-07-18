package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.model.biz.TicketDetailBiz;
import com.example.trainticket_01.view.interfaces.ITicketDetailView;

/**
 * Created by maomao on 2019/4/9.
 * <p>
 * 车票详情presenter
 */

public class TicketDetailPresenter
{
    private TicketDetailBiz ticketDetailBiz;
    private ITicketDetailView iTicketDetailView;

    public TicketDetailPresenter(ITicketDetailView iTicketDetailView)
    {
        ticketDetailBiz = new TicketDetailBiz();
        this.iTicketDetailView = iTicketDetailView;
    }

    public void doOrderTicket(OrderBean oldOrder, OrderBean orderBean)
    {
        ticketDetailBiz.doOrderTicket(oldOrder, orderBean, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iTicketDetailView.orderSuccess((OrderListBean)result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iTicketDetailView.orderFailed((String)msg);
            }
        });
    }
}
