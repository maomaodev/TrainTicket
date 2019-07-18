package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.model.biz.PayTicketBiz;
import com.example.trainticket_01.view.interfaces.IPayTicketView;

/**
 * Created by maomao on 2019/4/10.
 * <p>
 * 支付车票Presenter
 */

public class PayTicketPresenter
{
    private PayTicketBiz payTicketBiz;
    private IPayTicketView iPayTicketView;

    public PayTicketPresenter(IPayTicketView iPayTicketView)
    {
        payTicketBiz = new PayTicketBiz();
        this.iPayTicketView = iPayTicketView;
    }

    public void doPayTicket(OrderListBean orderListBean)
    {
        payTicketBiz.doPayTicket(orderListBean, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iPayTicketView.paySuccess((OrderListBean) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iPayTicketView.payFailed((String) msg);
            }
        });
    }
}
