package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.TicketListBean;
import com.example.trainticket_01.model.biz.QueryTicketBiz;
import com.example.trainticket_01.view.interfaces.IQueryTicketView;

/**
 * Created by maomao on 2019/4/8.
 * <p>
 * 车票查询presenter
 */

public class QueryTicketPresenter
{
    private QueryTicketBiz queryTicketBiz;
    private IQueryTicketView iQueryTicketView;

    public QueryTicketPresenter(IQueryTicketView iQueryTicketView)
    {
        queryTicketBiz = new QueryTicketBiz();
        this.iQueryTicketView = iQueryTicketView;
    }

    public void doQueryTicket(String from, String to, String date, String model)
    {
        queryTicketBiz.doQueryTicket(from, to, date, model, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iQueryTicketView.querySuccess((TicketListBean) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iQueryTicketView.queryFailed();
            }
        });
    }
}
