package com.example.imageselecter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static String log = "LYKMainActivity";

    @BindView(R.id.main_activity_button)
    Button button;

    @BindView(R.id.main_activity_image)
    ImageView imageView;

    @BindView(R.id.main_activity_framelayout)
    FrameLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        getPermission();
    }

    @OnClick(R.id.main_activity_button)
    void onButtonClick() {
        Log.i(log, "onButtonClick");
        ImageChooseFragment imageChooseFragment = new ImageChooseFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 这里不能用普通的Fragment的add方法来添加，必须用show。
        // 如果用的是前者，fragment的位置不是在底部
        imageChooseFragment.show(fragmentManager, "image_choose_fragment");
    }

    private void getPermission(){

        XXPermissions.with(this)
                // 申请单个权限
                .permission(Permission.READ_EXTERNAL_STORAGE)
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                // 设置权限请求拦截器（局部设置）
                //.interceptor(new PermissionInterceptor())
                // 设置不触发错误检测机制（局部设置）
                //.unchecked()
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(@NonNull List<String> permissions, boolean all) {
                        if (!all) {
//                            toast("获取部分权限成功，但部分权限未正常授予");
                            return;
                        }
//                        toast("获取录音和日历权限成功");
                    }

                    @Override
                    public void onDenied(@NonNull List<String> permissions, boolean never) {
                        if (never) {
//                            toast("被永久拒绝授权，请手动授予录音和日历权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(getApplicationContext(), permissions);
                        } else {
//                            toast("获取录音和日历权限失败");
                        }
                    }
                });
    }


}
