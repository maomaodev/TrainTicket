package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.OrderListBean;
import com.example.trainticket_01.model.bean.UserBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/11.
 * <p>
 * 查询订单逻辑
 */

public class QueryOrderBiz
{
    /**
     * 发起http网络请求查询订单
     *
     * @param userBean  用户实体类
     * @param orderType 订单状态
     * @param callBack  回调
     */
    public void doQueryOrder(UserBean userBean, int orderType, final CallBack callBack)
    {
        // 设置请求参数
        RequestParams params = new RequestParams(AppConfig.QUERY_ORDER);
        params.addParameter("userId", userBean.getUserId());
        params.addParameter("state", translateState(orderType));
        // 发送网络请求
        x.http().post(params, new Callback.CacheCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                OrderListBean orderListBean = gson.fromJson(result, OrderListBean.class);
                if ("success".equals(orderListBean.getResStatus()))
                    callBack.onSuccess(orderListBean.getOrders());
                else
                    callBack.onFailed(orderListBean.getResMsg());
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

    private String translateState(int orderType)
    {
        switch (orderType)
        {
            case AppConfig.ORDER_UNPAID:
                return "unpaid";
            case AppConfig.ORDER_NOW:
                return "wait";
            case AppConfig.ORDER_OLD:
                return "finished";
            default:
                return "";
        }
    }
}
