package com.fanhy.feature.main;

import android.view.View;

import com.fanhy.constant.Constants;

/**
 * 描述: 主界面点击事件
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-09-06 10:04
 */

public class MainHandler {
    private OnScaleCallback callback;

    public MainHandler(OnScaleCallback callback) {
        this.callback = callback;
    }

    public void scaleClick(View view) {
        Object viewTag = view.getTag();
        if (viewTag != null && Constants.currScale > 0.5 && Constants.currScale < 1.5) {
            if ("add".equals(viewTag.toString())) {// 增大
                Constants.currScale += 0.1;
            } else if ("sub".equals(viewTag.toString())) {// 缩小
                Constants.currScale -= 0.1;
            }
            if (callback != null) {
                callback.onResult(Constants.currScale);
            }
        }
    }

    /**
     * 缩放回调
     *
     */
    public interface OnScaleCallback {
        void onResult(Float scale);
    }
}
