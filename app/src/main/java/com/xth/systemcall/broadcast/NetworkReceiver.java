package com.xth.systemcall.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.xth.systemcall.base.LogUtil;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!= null &&networkInfo.isAvailable()){
            int type = networkInfo.getType();
            LogUtil.d(getClass().getSimpleName()+"---"+new Throwable().getStackTrace()[0].getMethodName() + " : " +type);
            switch (type){
                case ConnectivityManager.TYPE_MOBILE:
                    break;
                case ConnectivityManager.TYPE_WIFI:
                    break;
                default:
                    break;
            }
        }else{
            LogUtil.d(getClass().getSimpleName()+"---"+new Throwable().getStackTrace()[0].getMethodName() + " : " +"没网络");
        }

    }
}
