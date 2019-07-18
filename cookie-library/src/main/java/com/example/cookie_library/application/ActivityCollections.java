package com.example.cookie_library.application;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by maomao on 2019/4/2.
 * <p>
 * Activity堆栈管理类
 */

public class ActivityCollections
{
    private static Stack<Activity> activityStack;
    private static ActivityCollections instance;

    private ActivityCollections()
    {
    }

    /**
     * 单实例,UI无需考虑多线程同步问题
     *
     * @return
     */
    public static ActivityCollections getInstance()
    {
        if (instance == null)
        {
            instance = new ActivityCollections();
        }
        return instance;
    }

    /**
     * 将Activity添加到栈中
     *
     * @param activity
     */
    public void addActivity(Activity activity)
    {
        if (activityStack == null)
        {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前栈顶Activity
     */
    public Activity getCurrentActivity()
    {
        if (activityStack == null || activityStack.isEmpty())
            return null;
        return activityStack.lastElement();
    }

    /**
     * 将Activity从栈中移出并结束
     *
     * @param activity
     */
    public void finishActivity(Activity activity)
    {
        if (activity != null)
        {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束所有栈中的Activity
     */
    public void finishAllActivity()
    {
        int size = activityStack.size();
        for (int i = 0; i < size; i++)
        {
            if (null != activityStack.get(i))
                activityStack.get(i).finish();
        }
        activityStack.clear();
    }
}
