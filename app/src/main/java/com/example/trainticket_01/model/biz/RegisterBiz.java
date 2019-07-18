package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.ResultBean;
import com.example.trainticket_01.model.bean.UserBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/4.
 * <p>
 * 注册逻辑
 */

public class RegisterBiz
{
    /**
     * 发起http网络请求注册
     *
     * @param userBean 用户实体类
     * @param callBack 回调
     */
    public void doRegister(final UserBean userBean, final CallBack callBack)
    {
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.REGISTER);
        params.addParameter("account", userBean.getAccount());
        params.addParameter("password", userBean.getPassword());
        params.addParameter("realName", userBean.getRealName());
        params.addParameter("idNumber", userBean.getIdNumber());
        params.addParameter("userType", userBean.getUserType());
        // 发送请求
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
