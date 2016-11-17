package com.ruanmeng.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityStack {

    private static Stack<Activity> mActivityStack;
    private static ActivityStack instance;

    private ActivityStack() {
    }

    public static ActivityStack getScreenManager() {
        if (instance == null) {
            instance = new ActivityStack();
        }
        return instance;
    }

    // 退出当前Activity
    public void popActivity(){
        Activity activity = mActivityStack.lastElement();
        if(activity!=null){
            activity.finish();
            activity=null;
        }
    }

    // 退出栈顶Activity
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            mActivityStack.remove(activity);
            // mActivityStack.pop();
            activity = null;
        }
    }

    // 获得当前栈顶Activity
    public Activity currentActivity() {
        //lastElement()获取最后个子元素，这里是栈顶的Activity
        if(mActivityStack == null || mActivityStack.size() == 0){
            return null;
        }
        Activity activity = mActivityStack.lastElement();
        // Activity activity = mActivityStack.pop();
        return activity;
    }

    // 将当前Activity推入栈中
    public void pushActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
        // mActivityStack.push(activity);
    }

    // 是否包含当前Activity
    public boolean isContainsActivity(Class<?> cls) {
        if(mActivityStack.size() == 0){
            return false;
        }
        for(int i=0;i<mActivityStack.size();i++) {
            Activity activity = currentActivity();
            if (activity.getClass().equals(cls))
                return true;
        }
        return false;
    }

    // 退出栈中所有Activity
    public void popAllActivityExceptOne(Class<?> cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 弹出栈中所有Activity，保留指定的Activity
     */
    public void popAllActivityExcept(Class<?>... clss) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            for (Class<?> cls : clss) {
                if (activity.getClass().equals(cls)) {
                    break;
                }
            }
            popActivity(activity);
        }
    }

    /**
     * 弹出栈中所有Activity
     */
    public void popAllActivitys() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

}
