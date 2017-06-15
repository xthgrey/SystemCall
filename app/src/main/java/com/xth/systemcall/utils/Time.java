package com.xth.systemcall.utils;

import com.xth.systemcall.base.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XTH on 2017/6/12.
 */

public class Time {
    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        LogUtil.d(str);
        return str;
    }
}
