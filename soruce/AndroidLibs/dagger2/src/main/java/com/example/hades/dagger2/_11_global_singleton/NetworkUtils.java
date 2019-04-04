package com.example.hades.dagger2._11_global_singleton;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private Context mContext;
    private NetworkChannel mNetworkChannel;

    public NetworkUtils(Context mContext, NetworkChannel mNetworkChannel) {
        this.mContext = mContext;
        this.mNetworkChannel = mNetworkChannel;
    }

    public void info() {
        System.out.println(TAG + "@" + hashCode());
    }
}
