package com.fanhy.feature.bean;

/**
 * 描述: 用户信息
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-23 16:06
 */

public class UserBean {
    public String count;
    public String pwd;

    public UserBean() {
    }

    public UserBean(String count, String pwd) {
        this.count = count;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "count='" + count + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
