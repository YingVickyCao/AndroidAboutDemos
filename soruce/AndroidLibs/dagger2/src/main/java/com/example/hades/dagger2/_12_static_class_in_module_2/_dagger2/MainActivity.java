package com.example.hades.dagger2._12_static_class_in_module_2._dagger2;

import com.example.hades.dagger2.Log;

import javax.inject.Inject;

public class MainActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
//    DefaultA mIA;
            IA mIA;

    protected void onCreate() {
//        injectDagger1Renamed();
        injectDagger2();
        test();
    }

    private void injectDagger1Renamed() {
    }

    private void injectDagger2() {
        DaggerMainActivityComponent4Dagger2.builder().build().inject(this);
    }

    public void test() {
        Log.d(TAG, "IAnimal@" + mIA.hashCode() + "," + mIA.getNumber1().getInfo() + "," + mIA.getNumber2().getInfo());
    }
}
