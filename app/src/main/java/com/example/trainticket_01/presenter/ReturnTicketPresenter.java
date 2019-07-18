package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.biz.ReturnTicketBiz;
import com.example.trainticket_01.view.interfaces.IReturnTicketView;

/**
 * Created by maomao on 2019/4/11.
 * <p>
 * 退票Presenter
 */

public class ReturnTicketPresenter
{
    private ReturnTicketBiz returnTicketBiz;
    private IReturnTicketView iReturnTicketView;

    public ReturnTicketPresenter(IReturnTicketView iReturnTicketView)
    {
        returnTicketBiz = new ReturnTicketBiz();
        this.iReturnTicketView = iReturnTicketView;
    }

    public void doReturnTicket(OrderBean orderBean)
    {
        returnTicketBiz.doReturnTicket(orderBean, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iReturnTicketView.returnSuccess((String) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iReturnTicketView.returnFailed((String) msg);
            }
        });
    }
}
