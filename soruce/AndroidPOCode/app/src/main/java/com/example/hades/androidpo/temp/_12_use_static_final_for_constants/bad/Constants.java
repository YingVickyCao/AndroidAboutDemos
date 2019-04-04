package com.example.hades.androidpo._12_use_static_final_for_constants.bad;

public class Constants {
    static int intVal = 42;
    static String strVal = "Hello, world!";

    public void printInt() {
        System.out.println(intVal);
    }

    public void printString() {
        System.out.println(strVal);
    }
}
