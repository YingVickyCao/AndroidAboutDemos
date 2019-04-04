package com.example.hades.dagger2._2_inject_with_module._constructor_with_parameter;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class PersonActivity {
    private static final String TAG = PersonActivity.class.getSimpleName();
    @Inject
    Person mPerson;

    @Inject
    Person mPerson2;

    void onCreate() {
        PersonActivityComponent component = DaggerPersonActivityComponent.builder().build();
        component.inject(this);

        test();
    }


    private void test() {
        Log.d(TAG, mPerson.printInfo());
        Log.d(TAG, mPerson2.printInfo());
    }
}
