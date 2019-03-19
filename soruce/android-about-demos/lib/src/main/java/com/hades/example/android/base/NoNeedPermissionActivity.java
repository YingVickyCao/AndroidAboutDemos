package com.hades.example.android.base;

public class NoNeedPermissionActivity extends BaseActivity {
    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }
}