package com.example.hades.dagger2._11_global_singleton;

import javax.inject.Inject;

public class MainActivity {
    @Inject
    RxUtilsAbs rxUtilsAbs;

    @Inject
    NetworkUtils networkUtils;

    public void create() {
        App.getAppComponent().inject(this);

        rxUtilsAbs.info();
        networkUtils.info();
    }
}