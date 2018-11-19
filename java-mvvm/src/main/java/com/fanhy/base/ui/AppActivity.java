package com.fanhy.base.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fanhy.base.BaseViewModel;
import com.fanhy.util.DisplayUtil;
import com.fanhy.util.ProgressDialogUtil;
import com.fanhy.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by aYue on 2018/7/9.
 *
 * @version : ver 1.0
 */
public abstract class AppActivity<VM extends BaseViewModel> extends BaseActivity implements LifecycleRegistryOwner {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    protected LifecycleRegistry lifecycleRegistry;
    protected BaseViewModel mVModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        lifecycleRegistry = new LifecycleRegistry(this);
        super.onCreate(savedInstanceState);
    }

    /**
     * 屏蔽系统字体大小
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    /**
     * 缩放字体大小
     *
     * @param newScale
     */
    public void setScaleFont(float newScale) {
        Resources resource = getResources();
        Configuration configuration = resource.getConfiguration();
        configuration.fontScale = newScale;// 设置字体的缩放比例
        resource.updateConfiguration(configuration, resource.getDisplayMetrics());
    }

    protected void setViewModel(VM viewModel) {
        this.mVModel = viewModel;
        if (mVModel == null) return;
        mVModel.mLoginExpiresCommand.observe(this, aVoid -> showLoginDialog());
        mVModel.mLoadingCommand.observe(this, aVoid -> showLoading());
        mVModel.mLoadingCompleteCommand.observe(this, aVoid -> completeLoading());
        mVModel.mSuccessCommand.observe(this, msg -> toastSuccess((String) msg));
        mVModel.mFailCommand.observe(this, msg -> toastFail((String) msg));
        mVModel.mFailIdCommand.observe(this, resId -> toastFail(((Integer) resId).intValue()));
        mVModel.mNomalToastCommand.observe(this, msg -> toastNomal((String) msg));
        mVModel.mNoNetToastCommand.observe(this, aVoid -> toastNomal("没有网络"));
    }

    private void toastSuccess(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        ToastUtil.showDoSuccessToast(msg);
    }

    private void toastFail(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        ToastUtil.showDoFailToast(msg);
    }

    private void toastFail(int strId) {
        String str = null;
        try {
            str = getString(strId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) return;
        ToastUtil.showDoFailToast(str);
    }

    private void toastNomal(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        ToastUtil.showToastMessage(msg);
    }

    protected void showLoading(String msg) {
        if (!TextUtils.isEmpty(msg)) {
        }
    }

    protected void showLoading() {
        ProgressDialogUtil.showDialog(this, "");
    }

    protected void completeLoading() {
        ProgressDialogUtil.dismissDialog();
    }

    protected void showDialog(String title, String sure, String cancle) {
    }

    /**
     * 弹出登录提示对话框
     */
    protected void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否确认退出登录")
                .setNegativeButton("取消", null)
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
