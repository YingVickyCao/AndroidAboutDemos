package com.example.hades.dagger2._13_created_twice_when_live_together.dagger2;

import com.example.hades.dagger2._13_created_twice_when_live_together.B;

import javax.inject.Inject;

public class Dagger2Activity {
    @Inject
    B mB;

    public void onCreate() {
        DaggerDagger2ActivityComponent.builder().build().inject(this);

        System.out.println(mB.getInfo());
    }

}