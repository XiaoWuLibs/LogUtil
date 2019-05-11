package com.demo.logutil.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * toast工具类
 * 防止重复点击时，重复新建Toast
 * Created by  wsl
 * on 2019/5/10 17:38
 */
public class ToastUtils {

    private static Toast toast;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String showMessage) {
        if (toast == null) {
            toast = Toast.makeText(context, showMessage, Toast.LENGTH_SHORT);
        } else {
            toast.setText(showMessage);
        }
        toast.show();
    }

}
