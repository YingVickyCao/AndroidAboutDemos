package com.example.hades.tdd.junit.test_runners.parameterized;

import com.example.hades.tdd.junit.Fibonacci;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class FibonacciTest2 {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
        });
    }

    @Parameterized.Parameter // first data value (0) is default
    public /* NOT private */ int fInput;

    @Parameterized.Parameter(1)
    public /* NOT private */ int fExpected;

    @Test
    public void test() {
        Assert.assertEquals(fExpected, Fibonacci.compute(fInput));
    }
}