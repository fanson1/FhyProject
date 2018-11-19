package com.base.ui.mvvm.view;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.base.R;
import com.base.ui.BaseActivity;
import com.base.ui.mvvm.viewmodel.BaseVModel;
import com.base.util.dialog.ProgressUtil;
import com.base.util.hint.ToastUtil;

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
        mVModel.mLoginExpiresCommand.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                showLoginDialog();
            }
        });
        mVModel.mLoadingCommand.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                ProgressUtil.showPregress(BaseMVVMActivity.this);
            }
        });
        mVModel.mLoadingCompleteCommand.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                ProgressUtil.dissmissProgress();
            }
        });
        mVModel.mSuccessCommand.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                ToastUtil.showDoSuccessToast();
            }
        });
        mVModel.mFailCommand.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                ToastUtil.showDoFailtureToast();
            }
        });
        mVModel.mNomalToastCommand.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                ToastUtil.showToastMessage(o.toString());
            }
        });
        mVModel.mNoNetToastCommand.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                ToastUtil.showToastMessage(getString(R.string.toast_bad_net));
            }
        });
    }

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
