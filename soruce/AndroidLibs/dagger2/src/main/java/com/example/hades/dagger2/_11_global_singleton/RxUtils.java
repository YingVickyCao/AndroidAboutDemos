package com.example.hades.dagger2._11_global_singleton;

public class RxUtils extends RxUtilsAbs {
    private static final String TAG = RxUtils.class.getSimpleName();

    private Context mContext;

    public RxUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void info() {
        System.out.println(TAG + "@" + hashCode());
    }
}
