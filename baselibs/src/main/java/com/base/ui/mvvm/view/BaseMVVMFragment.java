package com.base.ui.mvvm.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.base.ui.BaseFragment;

/**
 * 描述: MVVM框架下View层Fragment基类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-10 14:34
 */

public abstract class BaseMVVMFragment extends BaseFragment implements LifecycleRegistryOwner{
    protected LifecycleRegistry lifecycleRegistry;
    public ViewModelProvider.Factory viewModelFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleRegistry = new LifecycleRegistry(this);
        viewModelFactory = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication());
        super.onCreate(savedInstanceState);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
