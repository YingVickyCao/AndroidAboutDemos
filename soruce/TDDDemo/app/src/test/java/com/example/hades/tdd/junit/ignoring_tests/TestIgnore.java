package com.example.hades.tdd.junit.ignoring_tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TestIgnore {

    @Ignore
    @Test
    public void test() {
        int num = 5;
        Assert.assertTrue(num > 6);
    }

    @Test
    @Ignore
    public void test2() {
        int num = 5;
        Assert.assertTrue(num > 6);
    }

    @Ignore
    public void test3() {
        int num = 5;
        Assert.assertTrue(num > 6);
    }
}