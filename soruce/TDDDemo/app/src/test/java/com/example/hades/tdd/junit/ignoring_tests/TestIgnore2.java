package com.example.hades.tdd.junit.ignoring_tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TestIgnore2 {

    @Test
    public void test() {
        int num = 5;
        Assert.assertTrue(num > 6);
    }

    @Ignore
    public void test2() {
        int num = 5;
        Assert.assertTrue(num > 6);
    }
}