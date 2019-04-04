package com.example.hades.dagger2._10_subgraphs._subclass._inject_last_child;

/*
    Error:(7, 10) java: [Dagger/MissingBinding] A cannot be provided without an @Inject constructor or an @Provides-annotated method.
      A is injected at FActivity.mA
      CActivity is injected at CActivityComponent.inject(FActivity)

 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
//        test.f();
        test.c();
    }

    private void f() {
        FActivity f = new FActivity();
        f.onCreate();
        f.testF();
    }

    private void c() {
        CActivity f = new CActivity();
        f.onCreate();
        f.testF();
        System.out.println();
        f.testC();
    }
}