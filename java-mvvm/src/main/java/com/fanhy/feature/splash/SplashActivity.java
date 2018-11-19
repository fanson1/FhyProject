package com.fanhy.feature.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fanhy.R;
import com.fanhy.base.ui.BaseActivity;
import com.fanhy.feature.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void beforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }).start();
    }

    @Override
    public void onClick(View v) {

    }
}
