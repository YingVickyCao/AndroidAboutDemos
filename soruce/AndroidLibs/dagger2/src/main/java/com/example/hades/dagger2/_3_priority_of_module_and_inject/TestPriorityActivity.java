package com.example.hades.dagger2._3_priority_of_module_and_inject;


import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class TestPriorityActivity {
    private static final String TAG = TestPriorityActivity.class.getSimpleName();
    @Inject
    Stu mStu;

    protected void onCreate() {
        DaggerStuComponent.builder().build().inject(this);

        test();
    }

    private void test() {
        Log.d(TAG, mStu.getName());
    }
}
