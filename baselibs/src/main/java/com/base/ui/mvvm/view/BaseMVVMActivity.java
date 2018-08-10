package com.base.ui.mvvm.view;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.base.ui.BaseActivity;
import com.base.ui.mvvm.viewmodel.BaseVModel;

/**
 * 描述: MVVM框架下View层Activity基类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-10 14:22
 */

public abstract class BaseMVVMActivity<VM extends BaseVModel> extends BaseActivity implements LifecycleRegistryOwner {

    public ViewModelProvider.Factory viewModelFactory;
    protected LifecycleRegistry lifecycleRegistry;
    protected BaseVModel mVModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        lifecycleRegistry = new LifecycleRegistry(this);
        viewModelFactory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        super.onCreate(savedInstanceState);
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
        mVModel.mNoNetToastCommand.observe(this, aVoid -> toastNomal(getString(0)));
    }

    /**
     *
     * 以下方法在项目中实现即可，无需在lib中实现
     */

    protected abstract void toastSuccess(String msg);

    protected abstract void toastFail(String msg);

    protected abstract void toastFail(int strId);

    protected abstract void toastNomal(String msg);

    protected abstract void showLoading(String msg);

    protected abstract void showLoading();

    protected abstract void completeLoading();

    protected abstract void showDialog(String title, String sure, String cancle);

    /**
     * 弹出登录提示对话框
     */
    protected abstract void showLoginDialog();

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
