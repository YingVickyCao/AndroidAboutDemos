package com.example.hades.dagger1.Instance;

import javax.inject.Inject;

public class Multi {
    @Inject
    public Multi() {
    }

    public int multi(int a, int b) {
        return a * b;
    }
}