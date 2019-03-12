package com.hades.example.android.android_about_demos;

import org.junit.Test;

public class InstanceOfCheck {
    @Test
    public void isNeedNUll() {
        Integer integer = null;

        if (integer instanceof Integer) {
            System.out.printf("not need null");
        }
    }
}
