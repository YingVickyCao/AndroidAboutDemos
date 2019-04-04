package com.example.hades.androidpo._2_memory_op.auto_boxing;

/**
 * Create by Vicky on 31/08/2018.
 **/
public class AutoBoxing {
    public void test() {
        Integer num = 0;
        for (int i = 0; i < 100; i++) {
            // AutoBoxing = int -> Integer
            num += i;
        }
        System.out.println(num);
    }
}