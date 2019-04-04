package com.example.hades.dagger2._4_organize_component._dependencies;

public class B {
    private static final String TAG = B.class.getSimpleName();
    private A mA;

//    @Inject
    public B(A a) {
        mA = a;
    }

    public String b() {
        return TAG + "@" + hashCode();
    }

    public String a() {
        return mA.a();
    }

    public String info() {
        return b() + "," + a();
    }
}