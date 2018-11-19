package com.fanhy.feature.login;

import android.view.View;

import com.fanhy.feature.bean.UserBean;

/**
 * 描述: 登陆点击事件
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-23 16:31
 */

public class LoginHandler {
    private LoginVM loginVM;
    public UserBean userBean;

    public LoginHandler(LoginVM loginVM, UserBean userBean) {
        this.loginVM = loginVM;
        this.userBean = userBean;
    }

    public void click(View view) {
        loginVM.login(userBean);
    }
}
