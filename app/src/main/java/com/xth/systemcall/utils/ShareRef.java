package com.xth.systemcall.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xth.systemcall.R;

/**
 * Created by XTH on 2017/6/15.
 */

public class ShareRef {
    private  SharedPreferences pref;
    private  SharedPreferences.Editor editor;
    public ShareRef(Context context) {
        pref = context.getSharedPreferences(Constant.SYSTEMCALL, context.MODE_PRIVATE);
        editor = pref.edit();
    }

    private void light(){
        pref.getInt(Constant.LIGHT, R.drawable.light_off);
        pref.getString(Constant.LIGHT,Constant.LIGHT);
        pref.getString(Constant.LIGHT+Constant.STATE,Constant.OFF);
    }
    private void setLight(String state){
        editor.putString(Constant.LIGHT+Constant.STATE,state);
        editor.apply();
    }

}
