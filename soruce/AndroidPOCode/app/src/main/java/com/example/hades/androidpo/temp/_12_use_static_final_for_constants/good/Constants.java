package com.example.hades.androidpo._12_use_static_final_for_constants.good;

public class Constants {
    static final int intVal = 42;
    static final String strVal = "Hello, world!";

    public void printInt() {
        System.out.println(intVal);
    }

    public void printString() {
        System.out.println(strVal);
    }
}
