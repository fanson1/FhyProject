package com.base.mvvm.net;

import android.app.Activity;
import android.content.Context;

import com.base.mvvm.util.DialogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述: 对观察者进行封装，实现其中部分方法
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-21 16:16
 */

public abstract class BaseObserver<T> implements Observer {
    private Activity activity;
    private String msg = "";// 进度对话框信息

    public BaseObserver(Activity activity, String msg) {
        this.activity = activity;
        this.msg = msg;
    }

    @Override
    public void onSubscribe(Disposable d) {
        DialogUtil.getInstance().showDialog(activity, msg);
    }

    @Override
    public void onNext(Object value) {
        callBack((T)value);
    }

    @Override
    public void onError(Throwable e) {
//        DialogUtil.getInstance().dismissDialog();
    }

    @Override
    public void onComplete() {
//        DialogUtil.getInstance().dismissDialog();
    }

    public abstract void callBack(T t);
}
