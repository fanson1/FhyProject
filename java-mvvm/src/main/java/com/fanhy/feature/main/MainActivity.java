package com.fanhy.feature.main;

import android.view.View;

import com.fanhy.R;
import com.fanhy.base.ui.AppActivity;
import com.fanhy.databinding.ActivityMainBinding;

public class MainActivity extends AppActivity<MainVM> {
    private ActivityMainBinding mainBinding;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforeInitView() {
        mainBinding = (ActivityMainBinding) binding;
    }

    @Override
    public void initView() {
        mainBinding.setMainHandler(new MainHandler(new MainHandler.OnScaleCallback() {
            @Override
            public void onResult(Float scale) {
                setScaleFont(scale);
            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
