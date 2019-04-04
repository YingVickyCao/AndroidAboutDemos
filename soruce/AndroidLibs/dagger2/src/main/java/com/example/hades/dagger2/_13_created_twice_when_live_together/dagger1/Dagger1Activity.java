package com.example.hades.dagger2._13_created_twice_when_live_together.dagger1;

import com.example.hades.dagger2._13_created_twice_when_live_together.B;
import dagger.ObjectGraph;

import javax.inject.Inject1;

public class Dagger1Activity {
    @Inject1
    B mB;

    public void onCreate() {
        //Dagger 1
        final ObjectGraph objectGraph = ObjectGraph.create(new Dagger1ActivityModule());
        objectGraph.inject(this);

        System.out.println(mB.getInfo());
    }
}