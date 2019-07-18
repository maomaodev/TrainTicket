package com.example.trainticket_01.model.biz;

import com.example.cookie_library.interfaces.CallBack;
import com.example.trainticket_01.config.AppConfig;
import com.example.trainticket_01.model.bean.UserBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by maomao on 2019/4/6.
 * <p>
 * 修改信息逻辑
 */

public class ModifyInfoBiz
{
    /**
     * 发起http网络请求修改信息
     *
     * @param userBean 用户实体类
     * @param callBack 回调
     */
    public void doModifyInfo(final UserBean userBean, final CallBack callBack)
    {
        // 组装请求参数
        RequestParams params = new RequestParams(AppConfig.MODIFY_INFO);
        params.addParameter("userId", userBean.getUserId());
        params.addParameter("realName", userBean.getRealName());
        params.addParameter("idNumber", userBean.getIdNumber());
        params.addParameter("userType", userBean.getUserType());
        // 发送网络请求
        x.http().post(params, new Callback.CacheCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Gson gson = new Gson();
                UserBean resultBean = gson.fromJson(result, UserBean.class);
                resultBean.setPassword(userBean.getPassword()); // 后台未返回密码
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
