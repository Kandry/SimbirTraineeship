package com.kozyrev.simbirtraineeship.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.kozyrev.simbirtraineeship.R;

public class AppPermissions {

    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 100;
    public static final int REQUEST_READ_EXTERNAL_STORAGE = 101;
    public static final int REQUEST_CAMERA = 102;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean getNeedPermissions(String[] permissions, int requestCode, Activity activity){
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
                if (activity.shouldShowRequestPermissionRationale(permission)){
                    Toast.makeText(activity, R.string.get_need_permissions, Toast.LENGTH_SHORT).show();
                }
                activity.requestPermissions(permissions, requestCode);
                return false;
            }
        }
        return true;
    }
}
