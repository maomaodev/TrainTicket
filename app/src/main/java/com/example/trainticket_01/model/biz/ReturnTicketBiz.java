package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.OrderBean;
import com.example.trainticket_01.model.bean.ResultBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/11.
 * <p>
 * 退票逻辑
 */

public class ReturnTicketBiz
{
    /**
     * 发起http网络请求退票
     *
     * @param orderBean 订单实体类
     * @param callBack  回调
     */
    public void doReturnTicket(OrderBean orderBean, final CallBack callBack)
    {
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.RETURN_TICKET);
        params.addParameter("userId", orderBean.getUserId());
        params.addParameter("ticketId", orderBean.getTicketId());
        // 发送网络请求
        x.http().post(params, new Callback.CacheCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                ResultBean resultBean = gson.fromJson(result, ResultBean.class);
                if ("success".equals(resultBean.getResStatus()))
                    callBack.onSuccess(resultBean.getResMsg());
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
