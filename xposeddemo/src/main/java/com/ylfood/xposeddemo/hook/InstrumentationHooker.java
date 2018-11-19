package com.ylfood.xposeddemo.hook;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;

/**
 * 描述: 自定义的Instrumentation对象用于替换目标Activity实例
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-11-02 9:52
 */

public class InstrumentationHooker extends Instrumentation {
    @Override
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application,
                                Intent intent, ActivityInfo info, CharSequence title, Activity parent,
                                String id, Object lastNonConfigurationInstance)
            throws IllegalAccessException, InstantiationException {

        return super.newActivity(clazz, context, token, application, intent, info, title, parent, id,
                lastNonConfigurationInstance);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        Activity activity = createActivity(intent);
        if (activity != null) {
            return activity;
        }
        return super.newActivity(cl, className, intent);
    }

    protected Activity createActivity(Intent intent) {
        String className = intent.getComponent().getClassName();
        // 判断是否为hook目标
        if ("com.ylfood.xposeddemo.MainActivity".equals(className)) {
            try {
                // 反射获取用于替代目标的Activity实例
                return (Activity) Class.forName("com.ylfood.xposeddemo.hook.HookActivity").newInstance();
            } catch (Exception e) {

            }
        }
        return null;
    }
}
