package com.hades.android.example.android_about_demos.apply_theme;

public class SFMock {

    private static SFMock mInstance;

    private boolean mIsRedTheme;

    public static SFMock getInstance() {
        if (null == mInstance){
            mInstance  = new SFMock();
        }
        return mInstance;
    }
    boolean isRedTheme() {
        return mIsRedTheme;
    }

    void useRedTheme(boolean isRedTheme) {
        this.mIsRedTheme = isRedTheme;
    }
}
