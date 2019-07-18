package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.UserBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 登录逻辑
 */

public class LoginBiz
{
    /**
     * 发起http网络请求登录
     *
     * @param userBean 用户实体类
     * @param callBack 回调
     */
    public void doLogin(final UserBean userBean, final CallBack callBack)
    {
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.LOGIN);
        params.addParameter("account", userBean.getAccount());
        params.addParameter("password", userBean.getPassword());
        // 发送网络请求
        x.http().post(params, new Callback.CacheCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                // 将返回的json格式数据映射为UserBean对象
                UserBean resultBean = gson.fromJson(result, UserBean.class);
                resultBean.setAccount(userBean.getAccount());
                resultBean.setPassword(userBean.getPassword());
                // 回调处理
                if ("success".equals(resultBean.getResStatus()))
                    callBack.onSuccess(resultBean);
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
