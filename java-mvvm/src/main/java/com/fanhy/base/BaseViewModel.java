package com.fanhy.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.support.annotation.NonNull;

import com.fanhy.util.SingleLiveEvent;

/**
 * Created by aYue on 2018/7/6.
 *
 * @version : ver 1.0
 * MVVM BaseViewModel (ViewModel 不再持有 View，而是 store and manage UI-related data)
 * ViewModel objects are scoped to the Lifecycle passed to the ViewModelProvider when getting the ViewModel.
 * The ViewModel stays in memory until the Lifecycle it’s scoped to goes away permanently
 * —in the case of an activity, when it finishes;
 * in the case of a fragment, when it’s detached.
 * @see <a href="https://developer.android.com/topic/libraries/architecture/viewmodel.html">ViewModel</a>
 */
public class BaseViewModel<M extends IModel> extends AndroidViewModel implements LifecycleObserver {

    protected M mModel;

    //登录过期提示
    public final SingleLiveEvent<Void> mLoginExpiresCommand = new SingleLiveEvent<>();
    //加载进度条
    public final SingleLiveEvent<Void> mLoadingCommand = new SingleLiveEvent<>();
    //取消进度条
    public final SingleLiveEvent<Void> mLoadingCompleteCommand = new SingleLiveEvent<>();
    //成功返回提示
    public final SingleLiveEvent<String> mSuccessCommand = new SingleLiveEvent<>();
    //失败返回提示
    public final SingleLiveEvent<String> mFailCommand = new SingleLiveEvent<>();
    //失败返回提示
    public final SingleLiveEvent<Integer> mFailIdCommand = new SingleLiveEvent<>();
    //toast提示
    public final SingleLiveEvent<String> mNomalToastCommand = new SingleLiveEvent<>();
    //toast无网提示
    public final SingleLiveEvent<Void> mNoNetToastCommand = new SingleLiveEvent<>();

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        this.mModel = model;
    }


}
