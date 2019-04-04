package com.example.hades.dagger1.Instance;

import javax.inject.Inject;

public class Plus {
    @Inject
    public Plus() {
    }

    public int plus(int a) {
        return a + 1;
    }
}