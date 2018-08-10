package com.base.textviewdemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.base.textviewdemo.aop.DoubleClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tv = (Button) findViewById(R.id.tv);

        SpannableString spanStr = new SpannableString("结算(10)");
        spanStr.setSpan(new AbsoluteSizeSpan(24), 2, spanStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spanStr);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fanhy", "执行结算操作");
                startActivity(new Intent(MainActivity.this, ObjectAnimatorActivity.class));
            }
        });

        Button btnAddCar = (Button) findViewById(R.id.tv0);
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fanhy", "执行加购物车操作");
                startActivity(new Intent(MainActivity.this, ConstraintLayoutActivity.class));
            }
        });


        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        Log.d("fanhy", "堆内存大小：" + heapSize);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
