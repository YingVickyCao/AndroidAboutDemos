package com.example.hades.dagger2._4_organize_component._extends;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class TestExtendActivity {
    private static final String TAG = TestExtendActivity.class.getSimpleName();

    @Inject
    A mA1;

    @Inject
    A mA2;

    @Inject
    B mB1;

    @Inject
    B mB2;

    protected void onCreate() {
        DaggerTestExtendComponent.builder()
                .appModule(new AppModule(App.getApplication()))
                .bModule(new BModule())
                .build()
                .inject(this);

        click();
    }

    private void click() {
        Log.d(TAG, "click:mA1=" + mA1.a() + ",mA2=" + mA2.a());
        Log.d(TAG, "click:mB1=" + mB1.info() + ",mB2=" + mB2.info());
    }
}
