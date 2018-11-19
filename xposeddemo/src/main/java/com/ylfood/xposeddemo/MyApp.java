package com.ylfood.xposeddemo;

import android.app.Application;

import com.ylfood.xposeddemo.hook.HookManager;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-11-02 10:14
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            // 初始化Hook配置
            HookManager.init();
            HookManager.injectInstrumentation();
        } catch (Exception e) {}
    }
}
