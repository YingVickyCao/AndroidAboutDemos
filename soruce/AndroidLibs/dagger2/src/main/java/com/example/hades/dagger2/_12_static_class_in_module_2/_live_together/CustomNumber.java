package com.example.hades.dagger2._12_static_class_in_module_2._live_together;

public class CustomNumber {
    int n;

    public static CustomNumber one() {
        CustomNumber customNumber = new CustomNumber();
        customNumber.n = 1;
        return customNumber;
    }

    public static CustomNumber two() {
        CustomNumber customNumber = new CustomNumber();
        customNumber.n = 2;
        return customNumber;
    }

    public int getInfo(){
        return n;
    }
}
