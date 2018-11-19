package com.base.util.hint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.R;

/**
 * 描述: Toast工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-23 10:06
 */

public class ToastUtil {
    private static Context context;
    private static Resources res;

    public static void initToast(Context context) {
        ToastUtil.context = context;
        res = context.getResources();
    }

    /**
     * 普通Toast在底部展示
     *
     * @param message
     */
    public static void showToastMessage(String message) {
        checkToastUtilIsInit();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 普通Toast在底部展示
     *
     * @param message
     */
    public static void showToastMessage(String message, boolean isLong) {
        checkToastUtilIsInit();
        Toast.makeText(context, message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    /**
     * 操作成功Toast在中间位置展示
     */
    public static void showDoSuccessToast() {
        showDoToast(1, false);
    }

    /**
     * 操作成功Toast在中间位置展示
     */
    public static void showDoSuccessToast(boolean isLong) {
        showDoToast(1, isLong);
    }

    /**
     * 操作失败Toast在中间位置展示
     */
    public static void showDoFailtureToast() {
        showDoToast(2, false);
    }

    /**
     * 操作失败Toast在中间位置展示
     */
    public static void showDoFailtureToast(boolean isLong) {
        showDoToast(2, isLong);
    }

    /**
     * 操作结果Toast在中间位置展示
     */
    public static void showDoAlertToast() {
        showDoToast(2, false);
    }

    /**
     * 操作结果Toast在中间位置展示
     */
    public static void showDoAlertToast(boolean isLong) {
        showDoToast(2, isLong);
    }

    /**
     * 弹出自定义样式Toast
     *
     * @param contentId
     * @param iconId
     * @param isLong
     */
    public static void showDefineToast(int contentId, int iconId, boolean isLong) {
        checkToastUtilIsInit();
        Toast toast = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflater.inflate(R.layout.toast_operation_layout, null);
        /*TextView textView = toastView.findViewById(R.id.tvContentHint);
        ImageView ivIcon = toastView.findViewById(R.id.ivIcon);
        if (textView != null) {
            textView.setText(res.getString(contentId));
        }
        if (ivIcon != null) {
            ivIcon.setImageResource(iconId);
        }*/

        toast.setDuration(isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 弹出自定义样式Toast
     *
     * @param content
     * @param drawable
     * @param isLong
     */
    public static void showDefineToast(String content, Drawable drawable, boolean isLong) {
        checkToastUtilIsInit();
        Toast toast = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflater.inflate(R.layout.toast_operation_layout, null);
        /*TextView textView = toastView.findViewById(R.id.tvContentHint);
        ImageView ivIcon = toastView.findViewById(R.id.ivIcon);
        if (textView != null) {
            textView.setText(content);
        }
        if (ivIcon != null) {
            ivIcon.setImageDrawable(drawable);
        }*/

        toast.setDuration(isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 弹出自定义样式Toast
     *
     * @param flag   1代表成功，2代表失败，3代表警示
     * @param isLong 是否长时间展示
     */
    private static void showDoToast(int flag, boolean isLong) {
        checkToastUtilIsInit();
        Toast toast = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflater.inflate(R.layout.toast_operation_layout, null);
        int contentId = 0;
        int iconId = 0;
        switch (flag) {
            case 1:
                contentId = R.string.toast_operation_success;
                iconId = R.drawable.path_success;
                break;
            case 2:
                contentId = R.string.toast_operation_failure;
                iconId = R.drawable.path_failure;
                break;
            case 3:
                contentId = R.string.toast_operation_result;
                iconId = R.drawable.path_alert;
                break;
        }
        /*TextView textView = toastView.findViewById(R.id.tvContentHint);
        ImageView ivIcon = toastView.findViewById(R.id.ivIcon);
        if (textView != null) {
            textView.setText(res.getString(contentId));
        }
        if (ivIcon != null) {
            ivIcon.setImageResource(iconId);
        }*/

        toast.setDuration(isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 检查ToastUtil是否初始化
     */
    private static void checkToastUtilIsInit() {
        if (context == null) {
            throw new RuntimeException("The ToastUtil was not init in your Application's onCreate()");
        }
    }
}
