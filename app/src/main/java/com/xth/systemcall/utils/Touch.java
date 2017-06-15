package com.xth.systemcall.utils;

import android.view.MotionEvent;

import com.xth.systemcall.base.LogUtil;

/**
 * Created by XTH on 2017/6/14.
 */

public class Touch {
    private float getStartX;
    private float getStartY;
    private float getEndX;
    private float getEndY;
    private int result = 0;
    private static final int MOVE_DISTANCE = 500;
    public static final int TWO_TO_FAR = 1;

    public int getResult() {
        return result;
    }

    private Touch() {
    }

    public static Touch getInstance() {
        return TouchHolder.touch;
    }

    private static class TouchHolder {
        private static final Touch touch = new Touch();
    }

    private void getPointerCount(MotionEvent event) {
        LogUtil.v("getPointerCount" + event.getPointerCount());
        switch (event.getPointerCount()) {
            case 1:
                break;
            case 2:
                countTouch(event);
                break;
            default:
                break;
        }
    }

    private void countTouch(MotionEvent event) {
        float distanceX;
        float distanceY;
        distanceX = Math.abs(getStartX - getEndX);//第一次触摸两点间的X距离
        distanceY = Math.abs(getStartY - getEndY);//第一次触摸两点间的Y距离
        LogUtil.v("distanceX:"+distanceX+"-distanceY:"+distanceY);
        distanceX = distanceX - Math.abs(event.getX(0) - event.getX(1));//第一次间距 - 移动中的间距 = 位移距离
        distanceY = distanceY - Math.abs(event.getY(0) - event.getY(1));//第一次间距 - 移动中的间距 = 位移距离
        LogUtil.v("distanceX:"+distanceX+"-distanceY:"+distanceY);
        if (distanceX >= MOVE_DISTANCE || distanceY >= MOVE_DISTANCE) {//当位移距离大于设定值
            result = TWO_TO_FAR;
        }
        LogUtil.v("result:"+result);
    }

    //-1:双手手势靠近500
    public void touchEvent(MotionEvent event) {
        LogUtil.v("touchEvent" + event.getAction());
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN://在第一个点被按下时触发
                getStartX = event.getX(0);
                getStartY = event.getY(0);
                LogUtil.v("getStartX:"+getStartX+"-getStartY:"+getStartY);
                break;
            case MotionEvent.ACTION_UP://当屏幕上唯一的点被放开时触发
                getStartX = 0;
                getStartY = 0;
                getEndX = 0;
                getEndY = 0;
                result = 0;
                break;
            case MotionEvent.ACTION_POINTER_DOWN://当屏幕上已经有一个点被按住，此时再按下其他点时触发
                getEndX = event.getX(1);
                getEndY = event.getY(1);
                LogUtil.v("getEndX:"+getEndX+"-getEndY:"+getEndY);
                break;
            case MotionEvent.ACTION_POINTER_UP://当屏幕上有多个点被按住，松开其中一个点时触发
                break;
            case MotionEvent.ACTION_MOVE://当有点在屏幕上移动时触发
                getPointerCount(event);
                break;
            default:
                break;
        }
    }
}
