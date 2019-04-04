package com.example.hades.androidpo.temp._4_lazy_load;

import org.junit.Test;

public class LazyLoadTest {
    LazyLoad mLazyLoad = new LazyLoad();

    @Test
    public void good() throws Exception {
        mLazyLoad.bad(true);
    }

    @Test
    public void bad() throws Exception {
        mLazyLoad.bad(false);
    }
}