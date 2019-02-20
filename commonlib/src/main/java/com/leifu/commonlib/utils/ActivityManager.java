package com.leifu.commonlib.utils;

import android.app.Activity;
import android.content.Context;


import java.util.Stack;

/**
 * Created by ShenBotao on 2016/5/17.
 * Activity管理类：用于管理Activity和退出程序
 */
public class ActivityManager {
    // Activity栈
    private static Stack<Activity> activityStack;
    // 单例模式
    private static ActivityManager instance;

    private ActivityManager() {
    }

    /**
     * 单一实例
     */
    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void removeActivity() {
        Activity activity = activityStack.lastElement();
        removeActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void removeActivity(Class<?> cls) {
        try {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    removeActivity(activity);
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public boolean isActivitySurvive(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束指定类名的Activity  除了登录页面
     */
    public void finishWithOutActivity(Class<?> cls) {
//        for (Activity activity : activityStack) {
//            if (!activity.getClass().equals(cls)) {
//                removeActivity(activity);
//            }
//        }
        //上面代码会有bug这里进行了特殊处理，如果直接在循环里面remove会报concurrentmodificationexception 错误
//        for (int i = 0, size = activityStack.size(); i < size; i++) {
//            if (null != activityStack.get(i)) {
//                if (activityStack.get(i).getClass() != MainActivity.class) {
//                    activityStack.get(i).finish();
//                }
//            }
//        }
//        activityStack.clear();
    }

    /**
     * 结束所有Activity
     */
    public void removeAAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            removeAAllActivity();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
