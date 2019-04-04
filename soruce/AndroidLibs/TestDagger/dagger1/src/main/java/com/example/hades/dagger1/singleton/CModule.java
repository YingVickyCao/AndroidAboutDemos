package com.example.hades.dagger1.singleton;

import dagger.Module;
import dagger.Provides;

/**
 * FIX_ERROR:error: Graph validation failed: You have these unused @Provider methods:
 * 1. com.example.hades.dagger1.singleton.CModule.provideC()
 * Set library=true in your module to disable this check.
 */
@Module(injects = {A.class, B.class}, includes = BModule.class, library = true)
public class CModule {
    @Provides
    public C provideC(A a, B b) {
        return new C(a, b);
    }
}