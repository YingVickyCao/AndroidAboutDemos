package com.example.hades.dagger2._4_organize_component._dependencies;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class TestDependenceActivity {
    private static final String TAG = TestDependenceActivity.class.getSimpleName();

    @Inject
    A mA1;

    @Inject
    A mA2;

    @Inject
    B mB1;

    @Inject
    B mB2;

    protected void onCreate() {
        AppComponent appComponent = App.getApplication().getAppComponent();
        TestDependenceActivityComponent component = DaggerTestDependenceActivityComponent.builder()
                .appComponent(appComponent)
                .build();

        component.inject(this);
        Log.d(TAG, TAG + "@" + this.hashCode() + ",onCreate: AppComponent@" + appComponent.hashCode() + ",component@" + component.hashCode());

        click();
    }

    private void click() {
        Log.d(TAG, "click:mA1=" + mA1.a() + ",mA2=" + mA2.a());
        Log.d(TAG, "click:mB1=" + mB1.info() + ",mB2=" + mB2.info());
    }
}