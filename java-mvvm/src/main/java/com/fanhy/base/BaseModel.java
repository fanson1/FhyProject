package com.fanhy.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by aYue on 2018/7/6.
 *
 */
public abstract class BaseModel implements IModel {

    protected Context context;

    public BaseModel(Application application) {
        if (application != null) {
            context = application.getApplicationContext();
        }
    }


}
