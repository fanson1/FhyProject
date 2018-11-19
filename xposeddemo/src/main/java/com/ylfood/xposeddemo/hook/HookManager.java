package com.ylfood.xposeddemo.hook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述: HookManager主要作用是hook住Instrumentation对象替换成自定义的InstrumentationHooker对象
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-11-02 10:00
 */

public class HookManager {
    private static Object mActivityThread;

    public static void init() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThread = aClass.getDeclaredMethod("currentActivityThread");
        currentActivityThread.setAccessible(true);
        mActivityThread = currentActivityThread.invoke(null);
    }

    public static void injectInstrumentation() throws NoSuchFieldException, IllegalAccessException {
        if (mActivityThread == null) {
            return;
        }
        Field field = mActivityThread.getClass().getDeclaredField("mInstrumentation");
        field.setAccessible(true);
        field.set(mActivityThread, new InstrumentationHooker());
    }
}
