package com.base.demo.login

import android.view.View
import com.base.demo.R
import com.base.demo.bean.UserBean
import com.base.demo.databinding.ActivityLoginBinding
import com.base.demo.test.UseKotlin
import com.base.ui.BaseActivity

class LoginActivity : BaseActivity() {
    var loginBinding: ActivityLoginBinding ?= null
    var vm: LoginViewModel ?= null

    override fun getTitleBar(): View ? = null

    override fun onClick(v: View?) {
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun beforeInitView() {
        loginBinding = binding as ActivityLoginBinding
    }

    override fun initView() {
        CheckUserInfoUtil.checkUserNameAndUserPwdIsInvalidate(loginBinding as ActivityLoginBinding)// RxJava联合判断
        vm = LoginViewModel(this)
        var userBean = UserBean()
        loginBinding?.userBean = userBean
        loginBinding?.loginHandler = LoginHandler(this)

        UseKotlin().start()
    }

    override fun initData() {
    }
}
