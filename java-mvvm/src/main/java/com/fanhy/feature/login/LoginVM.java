package com.fanhy.feature.login;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.fanhy.base.BaseViewModel;
import com.fanhy.feature.bean.UserBean;
import com.fanhy.base.BaseCallback;

/**
 * 描述: 登陆ViewModel层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-23 16:09
 */

public class LoginVM extends BaseViewModel<LoginModel> {
    public MutableLiveData<Boolean> loginLiveData = new MutableLiveData<>();
    public LoginVM(@NonNull Application application) {
        super(application, new LoginModel(application));
    }

    public void login(UserBean userBean) {
        mLoadingCommand.call();
        mModel.login(userBean, new BaseCallback<Boolean, String>() {
            @Override
            public void onSuccess(Boolean successResult) {
                mLoadingCompleteCommand.call();
                loginLiveData.setValue(successResult);
            }

            @Override
            public void onFailture(String failtureDescr) {
                mLoadingCompleteCommand.call();
                mFailCommand.setValue(failtureDescr);
            }
        });
    }
}
