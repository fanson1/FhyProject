package com.fanhy.feature.login;

import android.app.Application;

import com.fanhy.base.BaseModel;
import com.fanhy.feature.bean.UserBean;
import com.fanhy.base.BaseCallback;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 登陆Model层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-23 16:07
 */

public class LoginModel extends BaseModel {
    public LoginModel(Application application) {
        super(application);
    }

    public void login(UserBean userBean, BaseCallback<Boolean, String> callback) {
        // 模拟网络请求
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    Thread.sleep(2000);
                    e.onNext("");
                } catch (InterruptedException ee) {
                    ee.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        boolean isSuccess = new Random().nextInt(10) % 2 == 0;
                        if (isSuccess) {
                            callback.onSuccess(isSuccess);
                        } else {
                            callback.onFailture("登录失败，请检查用户名和密码");
                        }
                    }
                });
    }
}
