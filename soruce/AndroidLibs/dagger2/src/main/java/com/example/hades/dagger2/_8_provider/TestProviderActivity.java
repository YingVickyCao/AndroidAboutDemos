package com.example.hades.dagger2._8_provider;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;
import javax.inject.Provider;

public class TestProviderActivity {
    private static final String TAG = TestProviderActivity.class.getSimpleName();
    @Inject
    A mA;

    @Inject
    A mA2;

    @Inject
    Provider<A> mAProvider;

    @Inject
    Provider<A> mAProvider2;

    protected void onCreate() {
        DaggerTestProviderActivityComponent.builder().build().inject(this);

        click();
    }

    private void click() {
        System.out.println();
        System.out.println("==========1=========>");
        Log.d(TAG, "click: mA =" + mA.hashCode());                   // mA =175475651
        Log.d(TAG, "click: mA2=" + mA2.hashCode());                  // mA2=523429237
        System.out.println("<=========1==========");

        System.out.println();
        System.out.println("==========2=========>");
        Log.d(TAG, "click: mAProvider =" + mAProvider.hashCode());   // mAProvider =664740647
        Log.d(TAG, "click: mAProvider2=" + mAProvider2.hashCode());  // mAProvider2=664740647
        System.out.println("<=========2==========");

        System.out.println();
        System.out.println("==========3=========>");
        A a1 = mAProvider.get();
        A a2 = mAProvider.get();
        A a3 = mAProvider2.get();
        Log.d(TAG, "click: a1=" + a1.info());   // a1=A@804564176
        Log.d(TAG, "click: a2=" + a2.info());   // a2=A@1421795058
        Log.d(TAG, "click: a3=" + a3.info());   // a2=A@1555009629
        System.out.println("<=========3==========");
    }
}
