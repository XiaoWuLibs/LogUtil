package com.demo.library.handler;

import android.os.Looper;
import android.widget.Toast;

import com.demo.library.application.LogUtilApplication;
import com.demo.library.log.LogUtils;

/**
 * Created by  wsl
 * on 2019/5/10 22:34
 * 异常捕获类
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static boolean PRINT_EXCEPTION_LOG = true;

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        //如果PRINT_EXCEPTION_LOG=true，则进行全局监测，并进行日志打印；如果为false则只进行全局监测，不打印日志
        if (PRINT_EXCEPTION_LOG) {
            LogUtils.log("crash", ex, LogUtils.ERROR, true);
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                //在此处处理出现异常的情况
                Toast.makeText(LogUtilApplication.getContext(), "程序开小差了呢..", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
        //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 设置是否打印exception日志
     *
     * @param printExceptionLog 是否打印崩溃日志.true:打印；false：不打印
     */
    public static void setPrintExceptionLog(boolean printExceptionLog) {
        PRINT_EXCEPTION_LOG = printExceptionLog;
    }
}
