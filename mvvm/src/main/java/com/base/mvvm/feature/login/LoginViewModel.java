package com.base.mvvm.feature.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.base.mvvm.bean.BaseBean;
import com.base.mvvm.bean.UserBean;
import com.base.mvvm.databinding.ActivityLoginBinding;
import com.base.mvvm.feature.main.order.list.OrderListActivity;
import com.base.mvvm.util.PrefrenceUtil;

/**
 * 描述: 登陆模块ViewModel实现
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-04-18 16:59
 */

public class LoginViewModel {
    private LoginModel model;
    private ActivityLoginBinding binding;

    public LoginViewModel(Activity activity, ActivityLoginBinding binding) {
        this.binding = binding;
        this.model = new LoginModel(activity, this);
    }

    public void login(UserBean userBean) {
        model.login(userBean);
    }

    public void setLoginResult(BaseBean<Integer> baseBean) {
        if (baseBean.status.equals("success")) {// 登陆成功
            binding.setBaseBean(baseBean);
            UserBean userBean = binding.getUserBean();
            if (userBean.isRecordPwd) {// 保存用户信息
                PrefrenceUtil.setUserBean(userBean);
            } else {// 清除用户信息
                PrefrenceUtil.clearUserBean();
            }
            binding.tvUserWelcome.setVisibility(View.VISIBLE);
            Context context = binding.tvUserWelcome.getContext();
            context.startActivity(new Intent(context, OrderListActivity.class));
            ((Activity)context).finish();
        } else {// 登陆失败
            binding.edtUserName.setText("");
            binding.edtUserPwd.setText("");
            binding.tvUserWelcome.setText("");
            binding.tvUserWelcome.setVisibility(View.GONE);
        }
    }
}
