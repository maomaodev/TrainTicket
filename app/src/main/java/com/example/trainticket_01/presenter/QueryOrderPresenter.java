package com.example.trainticket_01.presenter;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.UserBean;
import com.example.trainticket_01.model.biz.QueryOrderBiz;
import com.example.trainticket_01.view.interfaces.IQueryOrderView;

import java.util.List;

/**
 * Created by maomao on 2019/4/11.
 * <p>
 * 订单Presenter
 */

public class QueryOrderPresenter
{
    private QueryOrderBiz queryOrderBiz;
    private IQueryOrderView iQueryOrderView;

    public QueryOrderPresenter(IQueryOrderView iQueryOrderView)
    {
        queryOrderBiz = new QueryOrderBiz();
        this.iQueryOrderView = iQueryOrderView;
    }

    public void doQueryOrder(UserBean userBean, int orderType)
    {
        queryOrderBiz.doQueryOrder(userBean, orderType, new CallBack()
        {
            @Override
            public void onSuccess(Object result)
            {
                iQueryOrderView.querySuccess((List<OrderBean>) result);
            }

            @Override
            public void onFailed(Object msg)
            {
                iQueryOrderView.queryFailed((String) msg);
            }
        });
    }
}
