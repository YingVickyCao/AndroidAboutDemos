package com.example.hades.dagger2._6_qualifier;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;
import javax.inject.Named;

/*
TestProviderActivity: click: A@107835432,name=A
TestProviderActivity: click: A@196419905,name=B
 */
public class TestQualifierActivity {
    private static final String TAG = TestQualifierActivity.class.getSimpleName();
    //        @First
    @Named("First")
    @Inject
    Person mPerson;

    //    @Second
    @Named("Second")
    @Inject
    Person mPerson2;

    /**
     * Error:(10, 10) java: [Dagger/MissingBinding] Person cannot be provided without an @Inject constructor or an @Provides-annotated method.
     * Person is injected at
     * TestQualifierActivity.mPerson3
     * TestQualifierActivity is injected at
     * TestQualifierActivityComponent.inject(TestQualifierActivity)
     */
//    @Inject
//    Person mPerson3;

    @Inject
    Info mInfo;

    @Inject
    Info mInfo2;

    protected void onCreate() {
        DaggerTestQualifierActivityComponent.builder().build().inject(this);

        test();
    }

    private void test() {
        Log.d(TAG, mPerson.info());
        Log.d(TAG, mPerson2.info());
//        Log.d(TAG, mPerson3.info());

        Log.d(TAG, mInfo.info());
        Log.d(TAG, mInfo2.info());
    }
}
