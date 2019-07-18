package com.example.cookie_library.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by maomao on 2019/4/3.
 * <p>
 * 保存数据持久化技术SharedPreferences工具类
 */

public class SharedPreferencesUtils
{
    // 保存数据的文件名称
    public static final String FILE_NAME = "share_data";

    public SharedPreferencesUtils()
    {

    }

    /**
     * 保存数据，根据不同类型调用不同方法
     *
     * @param context 上下文
     * @param key     键
     * @param object  值
     */
    public static void put(Context context, String key, Object object)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String)
            editor.putString(key, (String) object);
        else if (object instanceof Integer)
            editor.putInt(key, (Integer) object);
        else if (object instanceof Boolean)
            editor.putBoolean(key, (Boolean) object);
        else if (object instanceof Float)
            editor.putFloat(key, (Float) object);
        else if (object instanceof Long)
            editor.putLong(key, (Long) object);
        else
            editor.putString(key, object.toString());

        editor.apply();
    }

    /**
     * 取出保存的数据，根据不同类型调用不同方法
     *
     * @param context       上下文
     * @param key           键
     * @param defaultObject 默认值
     * @return 键对应的值，找不到返回默认值
     */
    public static Object get(Context context, String key, Object defaultObject)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if (defaultObject instanceof String)
            return sp.getString(key, (String) defaultObject);
        else if (defaultObject instanceof Integer)
            return sp.getInt(key, (Integer) defaultObject);
        else if (defaultObject instanceof Boolean)
            return sp.getBoolean(key, (Boolean) defaultObject);
        else if (defaultObject instanceof Float)
            return sp.getFloat(key, (Float) defaultObject);
        else if (defaultObject instanceof Long)
            return sp.getLong(key, (Long) defaultObject);

        return null;
    }
}
