package com.xth.systemcall.hardware;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import com.xth.systemcall.base.LogUtil;
import com.xth.systemcall.utils.Constant;

/**
 * Created by XTH on 2017/6/15.
 */

public class Light {
    private static CameraManager mCameraManager;
    private static String mCameraId;
    private static Camera camera;
    private static Camera.Parameters parameters;

    public static Light getInstance(Context context) {

        Boolean isFlashAvailable = context.getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        LogUtil.v("isFlashAvailable" + isFlashAvailable);
        if (!isFlashAvailable) {
            AlertDialog alert = new AlertDialog.Builder(context).create();
            alert.setTitle("Error !!");
            alert.setMessage(Constant.NO_LIGHT);
            alert.show();
        } else {
            mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mCameraId = mCameraManager.getCameraIdList()[0];
                    camera = Camera.open();
                    parameters = camera.getParameters();
                } else {
                    camera = Camera.open();
                    parameters = camera.getParameters();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return LightHolder.light;
    }

    private static class LightHolder {
        private static Light light = new Light();
    }

    public Light() {

    }

    public void turnOnFlashLight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, true);
            } else {
                if (!Camera.Parameters.FLASH_MODE_TORCH.equals(parameters.getFlashMode())) {
                    // Turn on the flash
                    if (parameters.getSupportedFlashModes().contains(Camera.Parameters.FLASH_MODE_TORCH)) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnOffFlashLight() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, false);
            } else {
                if (!Camera.Parameters.FLASH_MODE_OFF.equals(parameters.getFlashMode())) {
                    if (parameters.getSupportedFlashModes().contains(Camera.Parameters.FLASH_MODE_OFF)) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.release();
                        camera = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
