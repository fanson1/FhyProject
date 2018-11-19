package com.base.util.dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * 描述: 加载进度对话框工具
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-02 10:46
 */

public class ProgressUtil {
    private static ProgressDialog progressDialog;

    public static void showPregress(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("loading...");
        } else {
            progressDialog.dismiss();
        }
        progressDialog.show();
    }

    public static void showPregress(Context context, String msg) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle(msg);
        } else {
            progressDialog.dismiss();
        }
        progressDialog.show();
    }

    public static void dissmissProgress() {
        if (progressDialog == null) {
            return;
        }
        progressDialog.dismiss();
    }
}
