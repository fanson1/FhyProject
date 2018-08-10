package com.base.ui.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.support.annotation.NonNull;

import com.base.ui.mvvm.livedata.SingleLiveData;
import com.base.ui.mvvm.model.IModel;

/**
 * 描述: ViewModel层基类，持有Model层引用
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-10 14:14
 */

public class BaseVModel<M extends IModel> extends AndroidViewModel implements LifecycleObserver {
    protected M mModel;


    //登录过期提示
    public final SingleLiveData<Void> mLoginExpiresCommand = new SingleLiveData<>();
    //加载进度条
    public final SingleLiveData<Void> mLoadingCommand = new SingleLiveData<>();
    //取消进度条
    public final SingleLiveData<Void> mLoadingCompleteCommand = new SingleLiveData<>();
    //成功返回提示
    public final SingleLiveData<String> mSuccessCommand = new SingleLiveData<>();
    //失败返回提示
    public final SingleLiveData<String> mFailCommand = new SingleLiveData<>();
    //失败返回提示
    public final SingleLiveData<Integer> mFailIdCommand = new SingleLiveData<>();
    //toast提示
    public final SingleLiveData<String> mNomalToastCommand = new SingleLiveData<>();
    //toast无网提示
    public final SingleLiveData<Void> mNoNetToastCommand = new SingleLiveData<>();

    public BaseVModel(@NonNull Application application, M model) {
        super(application);
        this.mModel = model;
    }
}
