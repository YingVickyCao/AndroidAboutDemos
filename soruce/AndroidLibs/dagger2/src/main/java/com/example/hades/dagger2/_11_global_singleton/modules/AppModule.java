package com.example.hades.dagger2._11_global_singleton.modules;

import com.example.hades.dagger2._11_global_singleton.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }
}
