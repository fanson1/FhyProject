package com.base.mvvm.util;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.base.mvvm.R;
import com.base.mvvm.app.MVVMApp;
import com.base.mvvm.databinding.ProgressLayoutBinding;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 描述: 对话框工具类
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-05-02 10:08
 */

public class DialogUtil {
    private static DialogUtil mDialogUtil;
    private static LayoutInflater inflater;
    private Dialog pd;
    private View contentView;
    private AVLoadingIndicatorView aviLoading;
    private TextView tvLoadingMsg;

    public static DialogUtil getInstance() {
        if (mDialogUtil == null) {
            synchronized (DialogUtil.class) {
                if (mDialogUtil == null) {
                    mDialogUtil = new DialogUtil();
                    inflater = (LayoutInflater) MVVMApp.getAppContext()
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                }
            }
        }
        return mDialogUtil;
    }

    public void showDialog(Context activity, String msg) {
        if (aviLoading != null && pd != null) {
            if (aviLoading.isShown()) {
                aviLoading.hide();
            }
            if (pd == null) {
                pd.dismiss();
            }
        }
        ProgressLayoutBinding plBinding = DataBindingUtil.inflate(inflater, R.layout.progress_layout,
                null, false);
        contentView = plBinding.llProgress;
        aviLoading = plBinding.aviProgress;
        tvLoadingMsg = plBinding.tvProgressMsg;
        if (msg != null && !msg.equals("")) {
            tvLoadingMsg.setText(msg);
        }
        aviLoading.setIndicator("com.base.mvvm.util.MyBallPulseSyncIndicator");
        pd = new CustomDialog(activity, R.style.CustomDialog);
        aviLoading.show();
        pd.show();
        pd.setContentView(contentView);
    }

    public void dismissDialog() {
        if(aviLoading == null || pd == null) {
            return;
        }

        aviLoading.hide();
        pd.dismiss();
    }
}
