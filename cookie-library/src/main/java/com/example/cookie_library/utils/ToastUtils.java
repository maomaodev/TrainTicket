package com.example.cookie_library.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 显示提示控件Toast的工具类
 */

public class ToastUtils
{
    public static Toast toast = null;

    public static final void show(Context context, int resId)
    {
        show(context, context.getResources().getString(resId));
    }

    public static final void show(Context context, @Nullable String text)
    {
        if (toast == null)
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        else
            toast.setText(text);
        toast.show();
    }
}
