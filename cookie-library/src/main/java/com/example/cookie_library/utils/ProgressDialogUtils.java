package com.example.cookie_library.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.cookie_library.R;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 显示加载控件ProgressDialog的提示工具类
 */

public class ProgressDialogUtils
{
    public static ProgressDialog dialog = null;

    public static void showProgress(Context context)
    {
        showProgress(context, R.string.waiting_tip, false);
    }

    public static void showProgress(Context context, int textRes)
    {
        showProgress(context, context.getString(textRes), false);
    }

    public static void showProgress(Context context, String text)
    {
        showProgress(context, text, true);
    }

    public static void showProgress(Context context, boolean cancelable)
    {
        showProgress(context, R.string.waiting_tip, cancelable);
    }

    public static void showProgress(Context context, int texRes, boolean cancelable)
    {
        showProgress(context, context.getString(texRes), cancelable);
    }

    /**
     * 显示加载控件
     *
     * @param context   上下文
     * @param textRes    显示文字
     * @param cancelable 是否可以取消
     */
    public static void showProgress(Context context, String textRes, boolean cancelable)
    {
            if (null == dialog)
                dialog = new ProgressDialog(context);
            dialog.setCancelable(cancelable);
            dialog.setMessage(textRes);
            dialog.show();
    }

    /**
     * 隐藏加载控件
     */
    public static void hideProgress()
    {
        if (dialog != null)
        {
            dialog.dismiss();
            dialog = null;
        }
    }
}