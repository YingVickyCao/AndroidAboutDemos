package com.hades.example.android.lib.base;

import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * checkPermission("Request SD card permission", Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
 */
public class RxPermissionsActivity extends BaseActivity {
    protected View mRoot;
    private RxPermissions rxPermissions;

    public void setRoot(View root) {
        mRoot = root;
    }

    protected void needCheckPermission() {
        if (isNeedCheckPermission()) {
            rxPermissions = new RxPermissions(this);
            rxPermissions.setLogging(true);
        }
    }

    protected boolean isNeedCheckPermission() {
        return true;
    }


    /**
     * @param permissions new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION}
     */
    protected void checkPermission(final String permissionRationale, final String... permissions) {
        if (!isNeedCheckPermission()) {
            return;
        }
        needCheckPermission();
        if (!isGranted(permissions)) {
            askUser2GrantPermissions(permissionRationale, permissions);
        }
    }

    private boolean isGranted(final String... permissions) {
        if (permissions == null) {
            throw new RuntimeException("permissions is null");
        }

        for (String permission : permissions) {
            if (!rxPermissions.isGranted(permission)) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param permissions Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION
     */
    protected void requestPermission(final String... permissions) {
        rxPermissions.request(permissions).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean granted) {
                if (granted) {
                    Toast.makeText(RxPermissionsActivity.this, "permission available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RxPermissionsActivity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * @param permissions Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION
     */
    protected void askUser2GrantPermissions(final String permissionRationale, final String... permissions) {
        rxPermissions.shouldShowRequestPermissionRationale(this, permissions).subscribe(shouldShowRequestPermissionRationale -> {
            if (shouldShowRequestPermissionRationale) {
                Snackbar.make(mRoot, permissionRationale, Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", view -> requestPermission(permissions))
                        .show();
            } else {
                requestPermission(permissions);
            }
        });
    }
}