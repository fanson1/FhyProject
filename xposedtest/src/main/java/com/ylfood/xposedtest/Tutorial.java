package com.ylfood.xposedtest;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-10-31 11:08
 */

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Tutorial implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam loadPackageParam) throws Throwable {
        //你想操作的应用的包名，这里为这个项目自身的包名
        if (loadPackageParam.packageName.equals("com.ylfood.xposeddemo")) {
            Class clasz = loadPackageParam.classLoader.loadClass("com.ylfood.xposeddemo.MainActivity");
            //hook MainActivity的 isEquls 方法
            XposedHelpers.findAndHookMethod(clasz, "isEquals", int.class, new XC_MethodHook() {
                //这俩个方法任选其一就可以达到效果
                //方法执行前进行的操作
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Integer para1 = (Integer) param.args[0];   //获取参数1
                    String s1 = Integer.toString(para1);
                    Log.v("hook before param1:", s1);
                    param.args[0] = 3;  //设置参数1，也就是将isEquls的参数恒定设为3
                    Log.v("hook", "before hook!");
                    XposedBridge.log("hook before param1:" + s1);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    boolean res = (boolean) param.getResult();
                    Log.v("hook after result :", res + "");
                    Integer para1 = (Integer) param.args[0];   //获取参数1
                    String s1 = Integer.toString(para1);
                    param.setResult(true);   //设置返回值，始终为true
                    XposedBridge.log("hook after result:true");
                    Log.v("hook param1:", s1);
                }
            });
        }
    }
}
