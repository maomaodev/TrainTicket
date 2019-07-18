package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.utils.DateTimeUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by maomao on 2019/4/9.
 * <p>
 * 车票详情逻辑
 */

public class TicketDetailBiz
{
    /**
     * 发起http网络请求提交订单
     * @param oldOrder  未完成的订单
     * @param orderBean 订单实体类
     * @param callBack  回调
     */
    public void doOrderTicket(OrderBean oldOrder, final OrderBean orderBean,
                              final CallBack callBack)
    {
        String dateTime = orderBean.getDate() + " " + orderBean.getStartTime();
        String timestamp = DateTimeUtils.getInstance().dataToTimestamp(dateTime);
        orderBean.setOrderId(timestamp);
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.ORDER_TICKET);
        params.addParameter("userId", orderBean.getUserId());
        params.addParameter("ticketId", orderBean.getTicketId());
        if(null != oldOrder)
            params.addParameter("oldTicketId", oldOrder.getTicketId());

        // 发送网络请求
        x.http().post(params, new Callback.CacheCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                OrderBean bean = gson.fromJson(result, OrderBean.class);
                OrderListBean orderListBean = new OrderListBean();
                ArrayList<OrderBean> arrayListBean = new ArrayList<>();

                orderBean.setUserId(bean.getUserId());
                orderBean.setTicketId(bean.getTicketId());
                orderBean.setCarriage(bean.getCarriage());
                orderBean.setSeat(bean.getSeat());
                orderBean.setResMsg(bean.getResMsg());
                orderBean.setResStatus(bean.getResStatus());

                arrayListBean.add(orderBean);
                orderListBean.setOrders(arrayListBean);
                if ("success".equals(bean.getResStatus()))
                    callBack.onSuccess(orderListBean);
                else
                    callBack.onFailed(orderBean.getResMsg());
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
