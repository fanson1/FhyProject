package com.base.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.base.util.app.AppManager;
import com.base.util.common.DisplayUtil;
import com.jaeger.library.StatusBarUtil;

/**
 * 描述: Activity基类
 * 主要处理Activity的视图数据初始化流程，键盘显示隐藏，沉浸式状态栏及Activity统一的栈管理
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-16 13:44
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected ViewDataBinding binding;
    protected BaseActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;// 保存Activity实例
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //如果存在actionBar，就隐藏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();// 隐藏ActionBar
        }
        StatusBarUtil.setColor(this, getStatusColor(), 50);
        binding = DataBindingUtil.setContentView(this, getContentViewId());// 获取DataBinding对象
        AppManager.getInstance().addActivity(this);// 将Activity实例添加到Activity栈中
        //setTranslucentStatus();// 设置沉浸式状态栏

        beforeInitView();// 初始化View之前的操作
        initView();// 初始化View操作
        initData();// 初始化数据操作

    }

    /**
     * 获取Activity布局文件id
     *
     * @return
     */
    public abstract int getContentViewId();//放layoutId

    /**
     * 获取标题栏视图
     *
     * @return
     */
    public abstract View getTitleBar();// 获取标题栏视图

    /**
     * 初始化View之前的操作
     */
    public abstract void beforeInitView();// 初始化View之前做的事

    /**
     * 初始化View视图：
     * 一般可在该方法中执行一些View的初始化工作，
     * 比如findViewById， inflate， 给View绑定监听器等
     */
    public abstract void initView();//初始化View

    /**
     * 初始化数据：
     * 一般可在该方法中执行一些数据初始化工作，
     * 比如全局成员的初始化，网络请求等
     */
    public abstract void initData();//初始化数据

    /**
     * 窗口焦点改变回调，可以在该方法中获取一些视图的宽高等信息
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            //setViewPaddingTopByStatusHeight();
        }
    }

    /**
     * 状态栏透明只有Android 4.4 以上才支持
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.setAttributes(layoutParams);
        }
    }

    /**
     * 不用强制转换的findviewbyid
     *
     * @param id        View的id
     * @param <T>       返回泛型
     * @return
     */
    public <T extends View> T findViewByIdNoCast(int id) {
        return (T) findViewById(id);
    }

    /**
     * 给指定id的View绑定点击监听
     *
     * @param ids 可变参数，要绑定的视图id
     */
    public void setOnClick(int... ids) {
        for (int id : ids)
            findViewById(id).setOnClickListener(this);

    }

    /**
     * 给指定的View绑定点击监听
     *
     * @param views 可变参数，要绑定的View
     */
    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }

    /**
     * 将指定id的View设置为返回按钮，点击该View执行返回键类似操作
     *
     * @param id 监听的View的id
     */
    protected void setToBack(int id) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 将指定View设置为返回按钮，点击该View执行返回键类似操作
     *
     * @param view 监听的View
     */
    protected void setToBack(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 取消掉输入键盘
     *
     * @param activity 对应界面的Activity实例
     */
    protected void dismissSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示键盘
     *
     * @param view 接收键盘输入的视图
     */
    protected void showKeyboard(View view) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给View设置内边距，其中paddingTop为状态栏高度，避免标题栏渗透到状态栏下，被状态栏挡住
     */
    protected void setViewPaddingTopByStatusHeight() {
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        int statusBarHeight = DisplayUtil.getStatusBarHeight(mActivity);
        if (statusBarHeight == 0) {
            statusBarHeight = 48;
        }
        view.setPadding(view.getPaddingLeft(), statusBarHeight,
                view.getPaddingRight(), view.getPaddingBottom());
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().remove(this);
        super.onDestroy();
    }

    /**
     * 状态栏默认颜色
     */
    private final int getDefaultTitleBarColor() {
        return Color.GRAY;
    }

    /**
     * 状态栏颜色：默认是指定配色，如果需要不同的状态栏颜色，可在子类中覆写该方法
     *
     * @return
     */
    protected int getStatusColor() {
        return getDefaultTitleBarColor();
    }
}