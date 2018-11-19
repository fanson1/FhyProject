package com.fanhy.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;

/**
 * 描述: 进度条对话框工具
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-23 15:45
 */

public class ProgressDialogUtil {
    private static ProgressDialog dialog;

    /**
     * 展示进度条对话框
     *
     * @param act
     * @param msg
     */
    public static void showDialog(Activity act, String msg) {
        if (act == null || act.isFinishing()) {
            return;
        }
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new ProgressDialog(act);
        if (TextUtils.isEmpty(msg)) {
            dialog.setMessage("努力加载中");
        } else {
            dialog.setMessage(msg);
        }
        dialog.show();
    }

    /**
     *
     * 取消进度条对话框
     */
    public static void dismissDialog() {
        if (dialog == null) {
            return;
        }
        dialog.dismiss();
    }
}
