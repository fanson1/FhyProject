package com.fanhy.base;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-23 16:12
 */

public interface BaseCallback<T, F> {
    void onSuccess(T successResult);
    void onFailture(F failtureDescr);
}
