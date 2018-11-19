package com.ylfood.xposeddemo.hook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ylfood.xposeddemo.R;

/**
 *
 * 用于替换目标的Activity
 */
public class HookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
    }
}
