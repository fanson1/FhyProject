package com.ylfood.xposedtest;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-11-01 14:36
 */

public class EdtHooker implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.d("fanhy", "包名：" + loadPackageParam.packageName);
        //你想操作的应用的包名，这里为这个项目自身的包名
        if (loadPackageParam.packageName.equals("com.ylfood.xposeddemo")) {

            Class clasz = loadPackageParam.classLoader.loadClass("com.ylfood.xposeddemo.MainActivity");
            //hook MainActivity的 isEquls 方法
            XposedHelpers.findAndHookMethod(clasz, "myClick", View.class, new XC_MethodHook() {
                //这俩个方法任选其一就可以达到效果
                //方法执行前进行的操作
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Field testCode = param.thisObject.getClass().getDeclaredField("testCode");
                    testCode.setAccessible(true);
                    testCode.set(param.thisObject, new Random().nextInt());
                    Log.d("EdtHooker", "afterHookedMethod: " + param.thisObject);
                    Log.d("EdtHooker", "afterHookedMethod: " + testCode.get(param.thisObject));

                    // 获取所有成员变量
                    Field[] fields = param.thisObject.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object object = field.get(param.thisObject);
                        if (object instanceof EditText) {
                            EditText edt = (EditText) object;
                            Log.d("EdtHooker", "afterHookedMethod: 输入框文字：" + edt.getText());
                        }
                    }

                    // 获取指定名称成员变量
                    Field edtNo = param.thisObject.getClass().getDeclaredField("edtNo");
                    Log.d("EdtHooker", "afterHookedMethod: edtNo = " + edtNo);
                    edtNo.setAccessible(true);
                    EditText editText = (EditText) edtNo.get(param.thisObject);
                    editText.setText("HelloWorld!");
                }
            });
        }
    }
}
