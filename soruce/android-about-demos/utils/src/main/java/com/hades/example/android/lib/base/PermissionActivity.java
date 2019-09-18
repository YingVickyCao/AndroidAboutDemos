package com.hades.example.android.lib.base;

import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hades.example.android.lib.R;

public class PermissionActivity extends BaseActivity {
    private static final String TAG = PermissionActivity.class.getSimpleName();

    private static final int REQUEST_CODE_4_REQUEST_PERMISSIONS = 2000;
    protected View mRoot;

    public void setRoot(View root) {
        mRoot = root;
    }

    /**
     * @param permissions new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION}
     */
    protected void checkPermission(final String permissionRationale, final String... permissions) {
        if (!isNeedCheckPermission()) {
            return;
        }
        if (!isGranted(permissions)) {
            askUser2GrantPermissions(permissionRationale, permissions);
        }
    }

    protected boolean isGranted(final String... permissions) {
        if (permissions == null) {
            throw new RuntimeException("permissions is null");
        }

        for (String permission : permissions) {
            if (!isGranted(permission)) {
                return false;
            }
        }
        return true;
    }

    private boolean isGranted(final String permission) {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, permission));
    }

    /**
     * @param permissions Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION
     */
    protected void requestPermission(final String... permissions) {
        String[] stringPermissions = new String[permissions.length];
        int index = 0;
        for (String p : permissions) {
            stringPermissions[index] = p;
            index++;
        }
        ActivityCompat.requestPermissions(this, stringPermissions, REQUEST_CODE_4_REQUEST_PERMISSIONS);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: requestCode=" + requestCode);
        switch (requestCode) {
            case REQUEST_CODE_4_REQUEST_PERMISSIONS: {
                onRequestPermissionsResult(requestCode, grantResults);
                break;
            }

            default:
                // other 'case' lines to check for other permissions this app might request
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        if (REQUEST_CODE_4_REQUEST_PERMISSIONS != requestCode) {
            Log.e(TAG, "onRequestPermissionsResult4SendMessage: requestCode is wrong");
            return;
        }

        boolean granted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;

        if (granted) {
            Toast.makeText(PermissionActivity.this, "permission available", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PermissionActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @param permissions Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION
     */
    private void askUser2GrantPermissions(final String permissionRationale, final String... permissions) {
        if (shouldShowRequestPermissionRationale(permissions)) {
            Snackbar.make(mRoot, permissionRationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, view -> requestPermission(permissions))
                    .show();
        } else {
            requestPermission(permissions);
        }

    }

    private boolean shouldShowRequestPermissionRationale(final String... permissions) {
        for (String p : permissions) {
            if (!isGranted(p) && !ActivityCompat.shouldShowRequestPermissionRationale(this, p)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return true;
    }
}