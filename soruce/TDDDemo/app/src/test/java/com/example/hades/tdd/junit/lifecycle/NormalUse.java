package com.example.hades.tdd.junit.lifecycle;

import com.example.hades.tdd.junit.Calculator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NormalUse {
    private static final String TAG = NormalUse.class.getSimpleName();

    @BeforeClass
    public static void prepare() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        // mCalculator = new Calculator(5, 3);
        mCalculator = new Calculator();
        mCalculator.setA(3);
        mCalculator.setB(5);
    }

    @Test
    public void add() throws Exception {
        System.out.println("@Test");
        Assert.assertEquals(8, mCalculator.add());
        Assert.assertEquals(3, mCalculator.add(1, 2));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("@After");
        mCalculator.clear();
        mCalculator = null;
    }

    @AfterClass
    public static void finish() throws Exception {

    }

    private Calculator mCalculator;
}