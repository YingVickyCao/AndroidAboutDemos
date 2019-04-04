package com.example.hades.dagger2._11_global_singleton.modules;

import com.example.hades.dagger2._11_global_singleton.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class UtilsModules {
    @Provides
    public RxUtilsAbs provideRxUtis(Context context) {
        return new RxUtils(context);
    }

    @Provides
    @Singleton
    public NetworkUtils provideNetworkUtils(Context context, NetworkChannel networkChannel) {
        return new NetworkUtils(context, networkChannel);
    }
}
