package com.fanhy.feature.main;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fanhy.base.BaseViewModel;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-09-06 10:15
 */

public class MainVM extends BaseViewModel<MainModel> {
    public MainVM(@NonNull Application application, MainModel model) {
        super(application, model);
    }
}
