package com.example.imageselecter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

    @OnClick(R.id.main_activity_button)
    void onButtonClick() {
        Log.i(log, "onButtonClick");
        ImageChooseFragment imageChooseFragment = new ImageChooseFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 这里不能用普通的Fragment的add方法来添加，必须用show。
        // 如果用的是前者，fragment的位置不是在底部
        imageChooseFragment.show(fragmentManager, "image_choose_fragment");
    }


}
