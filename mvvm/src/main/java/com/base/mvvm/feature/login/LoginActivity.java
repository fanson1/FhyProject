package com.base.mvvm.feature.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.base.mvvm.R;
import com.base.mvvm.bean.UserBean;
import com.base.mvvm.databinding.ActivityLoginBinding;
import com.base.mvvm.util.PrefrenceUtil;

/**
 * 描述: 登陆模块View层实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-18 16:59
 */

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        CheckUserInfoUtil.checkUserNameAndUserPwdIsInvalidate(binding);// RxJava联合判断
        binding.setClickHandler(new LoginHandler(this, binding));// 绑定监听
        UserBean userBean = PrefrenceUtil.getUserBean();
        if(userBean != null) {// 是否记住密码
            binding.cbIsRecordPwd.setChecked(userBean.isRecordPwd);
        } else {
            userBean = new UserBean();
        }
        binding.setUserBean(userBean);// 绑定用户信息
    }
}
