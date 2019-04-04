package com.example.hades.dagger2._7_lazy;


import com.example.hades.dagger2.Log;
import dagger.Lazy;

import javax.inject.Inject;

public class TestLazyActivity {
    private static final String TAG = TestLazyActivity.class.getSimpleName();
    @Inject
    A mA;

    @Inject
    A mA2;

    @Inject
    Lazy<A> mALazy;

    @Inject
    Lazy<A> mALazy2;

    protected void onCreate() {
        DaggerTestLazyActivityComponent.builder().build().inject(this);

        test();
    }

    private void test() {
        System.out.println("=======1======");

        Log.d(TAG, "test: mA=" + mA.hashCode());                   // mA=175475651
        Log.d(TAG, "test: mA2=" + mA2.hashCode());                  // mA2=175475651
        System.out.println();

        System.out.println("=======2======");
        Log.d(TAG, "test: mALazy=" + mALazy.hashCode());           // mALazy=92754485
        Log.d(TAG, "test: mALazy2=" + mALazy2.hashCode());         // mALazy2=262690506
        System.out.println();

        System.out.println("=======3======");
        A a1 = mALazy.get();
        A a2 = mALazy2.get();
        Log.d(TAG, "test: a1=" + a1.info());   // a1=A@193705849
        Log.d(TAG, "test: a2=" + a2.info());   // a2=A@193705849
    }
}
