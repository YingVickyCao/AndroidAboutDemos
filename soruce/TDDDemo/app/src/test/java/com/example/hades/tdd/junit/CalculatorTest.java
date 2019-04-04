package com.example.hades.tdd.junit;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void add() throws Exception {
        System.out.println("@Test");
        Calculator calculator = new Calculator();
        Assert.assertEquals(3, calculator.add(1, 2));
        Assert.assertEquals(4, calculator.add(1, 2));
    }
}