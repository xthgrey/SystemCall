package com.xth.systemcall.base;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by XTH on 2017/6/12.
 */

public class ActivityCollecter {
    public static ArrayList<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
