package com.icedcap.permissiondemo.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: doushuqi
 * Date: 16-8-23
 * Email: shuqi.dou@singuloid.com
 * LastUpdateTime:
 * LastUpdateBy:
 */
public class MainViewModel {
    private static final String TAG = "MainViewModel";
    public static final int REQUEST_LOCATION = 1053;
    private Context mContext;

    public MainViewModel(Context context) {
        mContext = context;
    }

    public void helloWorld() {
//        requestSinglePermission();
        requestMultiplePermission();
    }

    public void requestSinglePermission() {
        String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        int hasPermission = mContext.checkSelfPermission(locationPermission);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "First time request this permission or denied before!");
            ((Activity)mContext).requestPermissions(new String[]{locationPermission}, REQUEST_LOCATION);
        } else {
            Log.i(TAG, "This permission has been allowed!");
        }
    }

    public void requestMultiplePermission() {
        String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
        String calendarPermission = Manifest.permission.WRITE_CALENDAR;
        int hasLocPermission = mContext.checkSelfPermission(locationPermission);
        int hasCalendarPermission = mContext.checkCallingPermission(calendarPermission);
        List<String> permissions = new ArrayList<>(2);
        if (hasLocPermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(locationPermission);
        }
        if (hasCalendarPermission != PackageManager.PERMISSION_GRANTED) {
            permissions.add(calendarPermission);
        }
        if (!permissions.isEmpty()) {
            Log.i(TAG, "First time request there permissions or denied before!");
            ((Activity)mContext).requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_LOCATION);
        } else {
            Log.i(TAG, "There permissions have been allowed!");
        }
    }
}
