package com.example.cookie_library.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by maomao on 2019/4/4.
 * <p>
 * 软键盘打开关闭工具类
 */

public class KeyBoardUtils
{
    private KeyBoardUtils()
    {
    }

    /**
     * 打开软件盘
     *
     * @param editText 输入框
     * @param context  上下文
     */
    public static void openKeyboard(EditText editText, Context context)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param editText 输入框
     * @param context  上下文
     */
    public static void closeKeyboard(EditText editText, Context context)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
