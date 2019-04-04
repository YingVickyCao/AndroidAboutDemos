package com.example.hades.dagger2._4_organize_component._dependencies;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class App {
    private static final String TAG = App.class.getSimpleName();

    @Inject
    A mA1;

    @Inject
    A mA2;

    private AppComponent mAppComponent;

    private static App mApp;

    public static App getApplication() {
        if (null == mApp) {
            mApp = new App();
        }
        return mApp;
    }

    public void onCreate() {
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);
        Log.d(TAG, TAG + "@" + this.hashCode() + ",onCreate: AppComponent@" + mAppComponent.hashCode() + ",mA1=" + mA1.a() + ",mA2=" + mA2.a());
        System.out.println();

        new TestDependenceActivity().onCreate();
        System.out.println();

        new TestDependenceActivity().onCreate();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
