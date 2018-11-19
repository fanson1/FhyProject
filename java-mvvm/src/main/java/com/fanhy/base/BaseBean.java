package com.fanhy.base;

/**
 * 描述: 网络请求实体类基类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-19 17:51
 */

public class BaseBean<T> {
    public String status;// 状态：success    error
    public String messages;// 信息
    public T result;// 数据

    @Override
    public String toString() {
        return "BaseBean{" +
                "status='" + status + '\'' +
                ", messages='" + messages + '\'' +
                ", result=" + result +
                '}';
    }
}
