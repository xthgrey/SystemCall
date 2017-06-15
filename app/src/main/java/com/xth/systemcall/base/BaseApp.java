package com.xth.systemcall.base;

import android.app.Application;

import com.xth.systemcall.utils.LogcatHelper;

import org.litepal.LitePal;

/**
 * Created by XTH on 2017/6/14.
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        LogcatHelper.getInstance(this).start();
    }
}
