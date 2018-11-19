package com.fanhy.base.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.fanhy.util.AppManager;
import com.fanhy.util.DisplayUtil;


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
        mActivity = this;

        binding = DataBindingUtil.setContentView(this, getContentViewId());
        setStatusBar();
        //如果存在actionBar，就隐藏
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        AppManager.getInstance().addActivity(mActivity);

        beforeInitView();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public abstract int getContentViewId();//放layoutId

    public abstract void beforeInitView();//初始化View之前做的事

    public abstract void initView();//初始化View

    public abstract void initData();//初始化数据


    /**
     * 状态栏透明（Android 4.4 以上才支持）
     * add on 2018.5.11 by ymx
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            //加了这个属性状态栏设置颜色没效果
            if (isImmerseStatusBarTranslucent()) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //根据上面设置是否对状态栏单独设置颜色
            getLollipopTitleBarColor(getLollipopTitleBarColor());

            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
            ViewCompat.setFitsSystemWindows(mContentView.getChildAt(0), !isImmerseStatusBarTranslucent());
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            setViewPaddingTopByStatusHeight(getKitkatTitleBarColor());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !isChangeStatusBarTextColor()) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


    /**
     * 不用强制转换的findviewbyid
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T findViewByIdNoCast(int id) {
        return (T) findViewById(id);
    }

    public void setOnClick(int... ids) {
        for (int id : ids)
            findViewById(id).setOnClickListener(this);

    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);

    }

    /**
     * 将某个View设置为返回键
     *
     * @param view
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
     * 将某个View设置为返回键
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
     * 本段代码用来处理如果输入法还显示的话就消失掉输入键盘
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
     * 4.4状态栏颜色切换
     * @param color
     */
    public void setViewPaddingTopByStatusHeight(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            View mChildView = contentView.getChildAt(0);

            int statusBarHeight = DisplayUtil.getStatusBarHeight(this);
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
                if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                    lp.topMargin += statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }
            View statusBarView = contentView.getChildAt(0);
            if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
                //避免重复调用时多次添加 View
                statusBarView.setBackgroundColor(color);
                return;
            }
            statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    statusBarHeight);
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, 0,lp);
        }
    }

    /**
     * 是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
     * 子界面通过覆写修改策略
     */
    public boolean isChangeStatusBarTextColor() {
        return true;
    }

    /**
     * 是否沉浸式状态栏
     */
    public boolean isImmerseStatusBarTranslucent() {
        return false;
    }

    /**
     * 4.4状态栏颜色
     * 子界面可通过覆写修改
     */
    public int getKitkatTitleBarColor() {
        return getDefaultTitleBarColor();
    }
    /**
     * 5.0以上状态栏颜色
     * 子界面可通过覆写修改
     */
    public int getLollipopTitleBarColor() {
        return getDefaultTitleBarColor();
    }
    /**
     * 状态栏默认颜色
     */
    private final int getDefaultTitleBarColor(){
        return Color.WHITE;
    }
    /**
     * 5.0以上状态栏颜色切换
     */
    public final void getLollipopTitleBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            getWindow().setStatusBarColor(color);
        }
    }

    /**
     * 显示键盘
     *
     * @param view
     */
    protected void showKeyboard(View view) {
        try {
            InputMethodManager inputMethodManage = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManage.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        AppManager.getInstance().remove(mActivity);
        super.onDestroy();
    }
}