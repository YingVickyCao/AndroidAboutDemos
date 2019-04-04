package com.example.hades.dagger2._12_static_class_in_module_2._dagger1;

import com.example.hades.dagger2.Log;
import dagger.ObjectGraph;

import javax.inject.Inject1;

public class MainActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject1
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
    }

    public void test() {
        Log.d(TAG, "IAnimal@" + mIA.hashCode() + "," + mIA.getNumber1().getInfo() + "," + mIA.getNumber2().getInfo());
    }
}
