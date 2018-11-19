package com.base.application;

import android.app.Application;
import android.content.Context;

import com.base.util.hint.ToastUtil;
import com.dreamlive.cn.clog.CollectLog;

/**
 * 描述: Application基类，处理一些应用初始化工作
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-03-19 8:58
 */

public class BaseApplication extends Application {
    public static Context appContext;
    public static boolean isDownLoadApp;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        ToastUtil.initToast(appContext);
        //android6.0也不需要动态sd权限，
        //将错误日志写入到sd卡,默认为Android/data/包名/files/logs下面放这个目录下主要是为了不需要权限
        CollectLog clog = CollectLog.getInstance();
        clog.init(this);
    }
}
