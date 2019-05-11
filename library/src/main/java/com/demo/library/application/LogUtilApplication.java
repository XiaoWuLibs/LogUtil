package com.demo.library.application;

import android.app.Application;
import android.content.Context;

import com.demo.library.exception.NoInitException;
import com.demo.library.handler.CrashHandler;

/**
 * Created by  wsl
 * on 2019/5/10 17:55
 * LogUtilApplication
 */
public class LogUtilApplication extends Application {
    /**
     * Global application context.
     */
    private static Context sContext;

    /**
     * Construct of LogUtilApplication. Initialize application context.
     */
    public LogUtilApplication() {
        sContext = this;
    }

    /**
     * Deprecated. Use init(Context) instead.
     *
     * @param mContext Application context.
     */
    public static void init(Context mContext, boolean useCrashCatch) {
        sContext = mContext;
        if (useCrashCatch) {
            launchCrashCatch();
        }
    }

    /**
     * 启动全局异常捕获
     */
    public static void launchCrashCatch() {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }

    /**
     * 获取context
     *
     * @return Context
     */
    public static Context getContext() {
        if (sContext == null) {
            throw new NoInitException(NoInitException.APPLICATION_CONTEXT_IS_NULL);
        }
        return sContext;
    }
}
