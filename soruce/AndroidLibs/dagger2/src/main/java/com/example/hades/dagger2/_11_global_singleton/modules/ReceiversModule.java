package com.example.hades.dagger2._11_global_singleton.modules;

import com.example.hades.dagger2._11_global_singleton.NetworkChannel;
import dagger.Module;
import dagger.Provides;

@Module
public class ReceiversModule {
    @Provides
    public NetworkChannel provideNetworkChannel() {
        return new NetworkChannel();
    }
}
