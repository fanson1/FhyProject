package com.fanhy.feature.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.fanhy.R;
import com.fanhy.base.ui.AppActivity;
import com.fanhy.databinding.ActivityLoginBinding;
import com.fanhy.feature.bean.UserBean;
import com.fanhy.feature.main.MainActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 登陆View层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-23 16:09
 */
public class LoginActivity extends AppActivity<LoginVM> {
    private ActivityLoginBinding loginBinding;
    private LoginVM loginVM;

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void beforeInitView() {
        loginBinding = (ActivityLoginBinding) binding;
    }

    @Override
    public void initView() {
        loginVM = ViewModelProviders.of(this).get(LoginVM.class);
        setViewModel(loginVM);
        loginVM.loginLiveData.observe(this, result -> updateUI(result));
        UserBean userBean = new UserBean("", "");
        loginBinding.setLoginHandler(new LoginHandler(loginVM, userBean));
        checkEdtIsNull();
    }

    private void updateUI(Boolean result) {
        if (result) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void checkEdtIsNull() {
        Observable<CharSequence> countObser = RxTextView.textChanges(loginBinding.edtCount).skip(0);
        Observable<CharSequence> pwdObser = RxTextView.textChanges(loginBinding.edtPwd).skip(0);
        Observable.combineLatest(countObser, pwdObser, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence textCount, CharSequence textPwd) throws Exception {
                return !TextUtils.isEmpty(textCount) && !TextUtils.isEmpty(textPwd);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            loginBinding.btnLogin.setEnabled(true);
                        } else {
                            loginBinding.btnLogin.setEnabled(false);
                        }
                    }
                });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
