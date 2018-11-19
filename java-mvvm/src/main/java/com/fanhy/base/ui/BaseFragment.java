package com.fanhy.base.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * 描述: Fragment基类
 * 主要处理Fragment的视图数据初始化流程，点击事件绑定
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-16 13:44
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, LifecycleRegistryOwner {

    protected LifecycleRegistry lifecycleRegistry;
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    protected ViewDataBinding binding;
    protected View rootView;// 根视图
    protected Activity mActivity;// 附着Activity
    protected boolean isCreate = true;// 是否第一次加载

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleRegistry = new LifecycleRegistry(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        if (rootView == null) {
            binding = DataBindingUtil.inflate(inflater, getResource(), container,false);
            rootView = binding.getRoot();
            beforeInitView();
            initView(rootView);
            initData();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
    }

    protected <T extends View> T findViewByIdNoCast(int id) {
        return rootView == null ? null : (T) rootView.findViewById(id);
    }

    protected abstract int getResource();

    protected abstract void beforeInitView();

    protected abstract void initView(View rootView);

    protected abstract void initData();

    @Override
    public void onStart() {
        super.onStart();
        if(!isCreate) {// 第一次已经在onCreate中调用
            initData();// 刷新数据
        }
        isCreate = false;
    }

    /**
     * 根据id设置点击事件
     * @param ids
     */
    protected void setOnClick(int... ids) {

        for (int id : ids) {
            if (id != -1)
                findViewByIdNoCast(id).setOnClickListener(this);
        }
    }

    public void setOnClick(View... views) {
        for (View view : views)
            view.setOnClickListener(this);
    }

    @NonNull
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
