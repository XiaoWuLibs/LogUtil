package com.demo.logutil;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.library.log.LogUtils;
import com.demo.permissionlibrary.permission.PermissionHelper;
import com.demo.permissionlibrary.permission.PermissionInterface;

/**
 * demo测试首页
 */
public class MainActivity extends AppCompatActivity implements PermissionInterface {
    private TextView tv_message;
    private Context context;
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();
        initView();
        displayView();
    }

    private void initView() {
        tv_message = findViewById(R.id.tv_message);
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.log(context, "tag", "123465498746132", LogUtils.INFO, true);
            }
        });

    }

    private void displayView() {
    }

    @Override
    public int getPermissionsRequestCode() {
        return 1001;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    public void requestPermissionsSuccess() {
        Toast.makeText(context, "权限请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void requestPermissionsFail() {
        Toast.makeText(context, "请打开所需权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
