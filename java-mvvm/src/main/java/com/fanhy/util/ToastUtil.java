package com.fanhy.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanhy.MyApp;
import com.fanhy.R;

/**
 * 描述: Toast工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-23 10:06
 */

public class ToastUtil {
    private static Context context = MyApp.appContext;

    /**
     * 普通Toast在底部展示
     *
     * @param message
     */
    public static void showToastMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 操作成功Toast在中间位置展示
     */
    public static void showDoSuccessToast() {
        showDoToast(R.layout.toast_success_layout, "", true);
    }

    /**
     * 操作成功Toast在中间位置展示
     *
     * @param msg 提示信息
     */
    public static void showDoSuccessToast(String msg) {
        showDoToast(R.layout.toast_success_layout, msg, true);
    }

    /**
     * 操作失败Toast在中间位置展示
     */
    public static void showDoFailToast() {
        showDoToast(R.layout.toast_fail_layout, "", false);
    }

    /**
     * 操作失败Toast在中间位置展示
     *
     * @param msg 提示信息
     */
    public static void showDoFailToast(String msg) {
        showDoToast(R.layout.toast_fail_layout, msg, false);
    }

    /**
     * 操作失败Toast在中间位置展示
     *
     * @param msg 提示信息
     */
    public static void showDoFailtureHintToast(String msg) {
        showDoToast(R.layout.toast_failture_hint_layout, msg, false);
    }

    /**
     * 弹出自定义样式Toast
     *
     * @param layoutId  布局id
     * @param msg       展示信息
     * @param isSuccess 是否操作成功
     */
    private static void showDoToast(int layoutId, String msg, boolean isSuccess) {
        Toast toast = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflater.inflate(layoutId, null);
        if (!TextUtils.isEmpty(msg)) {
            TextView textView = null;
            if (isSuccess) {
                textView = (TextView) toastView.findViewById(R.id.tv_success_hint);
            } else {
                textView = (TextView) toastView.findViewById(R.id.tv_fail_hint);
            }
            textView.setText(msg);
        }
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 弹出的自定义普通的Toast
     *
     * @param mContext 上下文
     * @param message  展示信息
     */
    public static void showToastMessage(Context mContext, String message) {
        showDoToast(R.layout.toast_common_layout, message, true);
    }
}
