package com.demo.permissionlibrary.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

public class PermissionUtil {
//    危险权限（Dangerous Permissions）：
//    group:android.permission-group.CONTACTS
//    permission:android.permission.WRITE_CONTACTS
//    permission:android.permission.GET_ACCOUNTS
//    permission:android.permission.READ_CONTACTS
//
//    group:android.permission-group.PHONE
//    permission:android.permission.READ_CALL_LOG
//    permission:android.permission.READ_PHONE_STATE
//    permission:android.permission.CALL_PHONE
//    permission:android.permission.WRITE_CALL_LOG
//    permission:android.permission.USE_SIP
//    permission:android.permission.PROCESS_OUTGOING_CALLS
//    permission:com.android.voicemail.permission.ADD_VOICEMAIL
//
//    group:android.permission-group.CALENDAR
//    permission:android.permission.READ_CALENDAR
//    permission:android.permission.WRITE_CALENDAR
//
//    group:android.permission-group.CAMERA
//    permission:android.permission.CAMERA
//
//    group:android.permission-group.SENSORS
//    permission:android.permission.BODY_SENSORS
//
//    group:android.permission-group.LOCATION
//    permission:android.permission.ACCESS_FINE_LOCATION
//    permission:android.permission.ACCESS_COARSE_LOCATION
//
//    group:android.permission-group.STORAGE
//    permission:android.permission.READ_EXTERNAL_STORAGE
//    permission:android.permission.WRITE_EXTERNAL_STORAGE
//
//    group:android.permission-group.MICROPHONE
//    permission:android.permission.RECORD_AUDIO
//
//    group:android.permission-group.SMS
//    permission:android.permission.READ_SMS
//    permission:android.permission.RECEIVE_WAP_PUSH
//    permission:android.permission.RECEIVE_MMS
//    permission:android.permission.RECEIVE_SMS
//    permission:android.permission.SEND_SMS
//    permission:android.permission.READ_CELL_BROADCASTS





    /**
     * 判断是否有某个权限
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasPermission(Context context, String permission){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(context.checkSelfPermission(permission)  != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    /**
     * 弹出对话框请求权限
     * @param activity
     * @param permissions
     * @param requestCode
     */
    public static void requestPermissions(Activity activity, String[] permissions, int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, requestCode);
        }
    }

    /**
     * 返回缺失的权限
     * @param context
     * @param permissions
     * @return 返回缺少的权限，null 意味着没有缺少权限
     */
    public static String[] getDeniedPermissions(Context context, String[] permissions){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> deniedPermissionList = new ArrayList<>();
            for(String permission : permissions){
                if(context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED){
                    deniedPermissionList.add(permission);
                }
            }
            int size = deniedPermissionList.size();
            if(size > 0){
                return deniedPermissionList.toArray(new String[deniedPermissionList.size()]);
            }
        }
        return null;
    }
}
