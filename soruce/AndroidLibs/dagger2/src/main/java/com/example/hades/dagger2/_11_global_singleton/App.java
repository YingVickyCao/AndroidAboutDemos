package com.example.hades.dagger2._11_global_singleton;

import com.example.hades.dagger2._11_global_singleton.modules.AppModule;

import javax.inject.Inject;

public class App extends Context {
    private static AppComponent appComponent;
    @Inject
    RxUtilsAbs rxUtilsAbs;

    @Inject
    NetworkUtils networkUtils;

    private MainActivity mainActivity;

    public void create() {
        super.create();

        appComponent = buildComponent();
        appComponent.inject(this);

        rxUtilsAbs.info();
        networkUtils.info();
        System.out.println();

        mainActivity = new MainActivity();
        mainActivity.create();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
