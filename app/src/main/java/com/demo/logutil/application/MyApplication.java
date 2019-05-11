package com.demo.logutil.application;

import com.demo.library.application.LogUtilApplication;
import com.demo.library.log.LogUtils;

/**
 * Created by  wsl
 * on 2019/5/10 22:25
 * Application
 */
public class MyApplication extends LogUtilApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.launchCrashCatch();
    }
}
