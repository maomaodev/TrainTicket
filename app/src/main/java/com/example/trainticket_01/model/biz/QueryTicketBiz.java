package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.TicketListBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/8.
 * <p>
 * 查询车票逻辑
 */

public class QueryTicketBiz
{
    /**
     * 发起http网络请求查询车票
     *
     * @param from     出发站点
     * @param to       目的站点
     * @param date     出发时间
     * @param model    火车类型
     * @param callBack 回调
     */
    public void doQueryTicket(String from, String to, String date, String model,
                              final CallBack callBack)
    {
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.QUERY_TICKET);
        params.addParameter("fromStation", from);
        params.addParameter("toStation", to);
        params.addParameter("date", date);
        params.addParameter("trainType", model);
        // 发送网络请求
        x.http().post(params, new Callback.CommonCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                TicketListBean ticketListBean = gson.fromJson(result, TicketListBean.class);
                callBack.onSuccess(ticketListBean);
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
        });
    }
}