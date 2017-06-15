package com.xth.systemcall.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by XTH on 2017/6/12.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollecter.addActivity(this);
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollecter.removeActivity(this);
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //在 onPause 之后调用，像横竖屏切换的时候需要保存播放进度，可以调用
        //outState.putInt("test", 2);
        super.onSaveInstanceState(outState);
        LogUtil.d(this.getLocalClassName() + "---" + new Throwable().getStackTrace()[0].getMethodName() + " : ");
    }
}
