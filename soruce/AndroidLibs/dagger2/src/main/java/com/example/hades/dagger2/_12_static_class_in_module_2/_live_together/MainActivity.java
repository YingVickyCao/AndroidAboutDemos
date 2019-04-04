package com.example.hades.dagger2._12_static_class_in_module_2._live_together;

import com.example.hades.dagger2.Log;
import dagger.ObjectGraph;

import javax.inject.Inject;
import javax.inject.Inject1;

public class MainActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    @Inject1
//    DefaultA mIA;
            IA mIA;

    protected void onCreate() {
        injectDagger1Renamed();
    }

    private void injectDagger1Renamed() {
        //Dagger 1
        final ObjectGraph objectGraph = ObjectGraph.create(new MainActivityModule());
        objectGraph.inject(this);
        test();
    }

    private void injectDagger2() {
        DaggerMainActivityComponent4Dagger2.builder().build().inject(this);
    }

    public void test() {
        Log.d(TAG, "IAnimal@" + mIA.hashCode() + "," + mIA.getNumber1().getInfo() + "," + mIA.getNumber2().getInfo());
    }
}
