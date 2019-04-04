package com.example.hades.dagger2._10_subgraphs._subclass._inject_parent;

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