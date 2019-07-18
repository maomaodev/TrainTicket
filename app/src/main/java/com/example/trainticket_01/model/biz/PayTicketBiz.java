package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.model.bean.ResultBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/10.
 * <p>
 * 支付车票逻辑
 */

public class PayTicketBiz
{
    /**
     * 发起http网络请求支付车票
     *
     * @param orderListBean 订单列表实体类
     * @param callBack      回调
     */
    public void doPayTicket(final OrderListBean orderListBean, final CallBack callBack)
    {
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.PAY_TICKET);
        OrderBean orderBean = orderListBean.getOrders().get(0);
        params.addParameter("userId", orderBean.getUserId());
        params.addParameter("ticketId", orderBean.getTicketId());
        params.addParameter("price", orderBean.getPrice());
        // 发送网络请求
        x.http().post(params, new Callback.CacheCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                ResultBean resultBean = gson.fromJson(result, ResultBean.class);
                if ("success".equals(resultBean.getResStatus()))
                    callBack.onSuccess(orderListBean);
                else
                    callBack.onFailed(resultBean.getResMsg());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback)
            {
                callBack.onFailed(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex)
            {
            }

            @Override
            public void onFinished()
            {
            }

            @Override
            public boolean onCache(String result)
            {
                return false;
            }
        });
    }
}
