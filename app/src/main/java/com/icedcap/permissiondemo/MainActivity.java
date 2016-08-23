package com.icedcap.permissiondemo;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.icedcap.permissiondemo.databinding.ActivityMainBinding;
import com.icedcap.permissiondemo.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(new MainViewModel(this));
        mLinearLayout = binding.mainLinearLayout;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (String str : permissions) {
            Log.d(TAG, "permissions>>> " + str);
        }

        switch (requestCode) {
            case MainViewModel.REQUEST_LOCATION:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        // Handle permission granted
                        Log.i(TAG,"Request " + permissions[i] + " has been granted");
                        Snackbar.make(mLinearLayout,  "Request permission has been granted", Snackbar.LENGTH_SHORT).show();
                    } else {
                        // Handle permission denied
                        Log.i(TAG, "Request " + permissions[i] + " has been denied");
                        Snackbar.make(mLinearLayout, "Request permission has been denied", Snackbar.LENGTH_SHORT).show();
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


    }
}
