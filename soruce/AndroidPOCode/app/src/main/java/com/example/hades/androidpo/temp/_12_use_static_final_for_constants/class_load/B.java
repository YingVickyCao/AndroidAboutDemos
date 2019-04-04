package com.example.hades.androidpo._12_use_static_final_for_constants.class_load;

public class B {
    public static int f;

    static {
        System.out.println("init B");

    }

    public static void m() {
        System.out.println("invoke m");

    }
}
