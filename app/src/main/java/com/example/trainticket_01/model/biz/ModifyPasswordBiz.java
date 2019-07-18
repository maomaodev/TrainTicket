package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.UserBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/5.
 * <p>
 * 修改密码逻辑
 */

public class ModifyPasswordBiz
{
    /**
     * 发起http网络请求修改密码
     *
     * @param userBean    用户实体类
     * @param newPassword 新密码
     * @param callBack    回调
     */
    public void doModifyPassword(UserBean userBean, String newPassword, final CallBack callBack)
    {
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.MODIFY_PASSWORD);
        params.addParameter("account", userBean.getAccount());
        params.addParameter("oldPassword", userBean.getPassword());
        params.addParameter("newPassword", newPassword);
        // 发送网络请求
        x.http().post(params, new Callback.CacheCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(result, UserBean.class);
                if ("success".equals(userBean.getResStatus()))
                    callBack.onSuccess(userBean);
                else
                    callBack.onFailed(userBean.getResMsg());
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
