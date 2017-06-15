package com.xth.systemcall.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xth.systemcall.base.LogUtil;


public class BatteryReceiver extends BroadcastReceiver {
    private int batteryPercent = 1;

    public int getBatteryPercent() {
        return batteryPercent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getExtras().getInt("level");
        int scale = intent.getExtras().getInt("scale");
        batteryPercent = level * 100 / scale;
        LogUtil.d(getClass().getSimpleName()+"---"+new Throwable().getStackTrace()[0].getMethodName() + " : "+batteryPercent+"%" );
    }
}
