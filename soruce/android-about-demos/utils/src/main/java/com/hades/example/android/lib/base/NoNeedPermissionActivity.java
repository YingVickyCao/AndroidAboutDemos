package com.hades.example.android.lib.base;

public class NoNeedPermissionActivity extends BaseActivity {
    @Override
    protected boolean isNeedCheckPermission() {
        return false;
    }
}