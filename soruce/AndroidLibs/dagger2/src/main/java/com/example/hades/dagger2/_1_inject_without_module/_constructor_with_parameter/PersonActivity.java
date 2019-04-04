package com.example.hades.dagger2._1_inject_without_module._constructor_with_parameter;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class PersonActivity {
    private static final String TAG = PersonActivity.class.getSimpleName();
    @Inject
    Person mPerson;

    @Inject
    Person mPerson2;

    protected void onCreate() {
        DaggerPersonActivityComponent.create().inject(this);

        test();
    }

    private void test() {
        Log.d(TAG, mPerson.info());
        Log.d(TAG, mPerson2.info());
    }
}
