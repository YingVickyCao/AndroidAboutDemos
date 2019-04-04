package com.example.hades.tdd.junit.test_runners.parameterized;

import com.example.hades.tdd.junit.PrimeNumberChecker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

// ERROR: java.lang.Exception: Test class should have exactly one public zero-argument constructor
// ERROR: java.lang.IllegalArgumentException: Test class can only have one constructor
//@RunWith(Parameterized.class)

@RunWith(Parameterized.class)
public class TestParameterized {
    private Integer inputNumber;
    private Boolean expectedResult;
    private PrimeNumberChecker primeNumberChecker;

    @Before
    public void initialize() {
        primeNumberChecker = new PrimeNumberChecker();
    }

    /*public TestParameterized() {

    }*/

    public TestParameterized(Integer inputNumber, Boolean expectedResult) {
        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true}
        });
    }

    // ERROR: java.lang.IllegalArgumentException: wrong number of arguments
    /*@Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false, true},
                {19, true},
                {22, false},
                {23, true}
        });
    }*/

    /*@Parameterized.Parameters
    public static Collection primeNumbers() {
        // TODO:有两个@Parameterized.Parameters标记时测试函数时，不知道时哪个生效。
        // primeNumbers##teamcity[suiteTreeStarted name='|[0|]' locationHint='java:suite://com.example.hades.tdd.junit.api.test_parameters.TestParameterized.|[0|]']
        System.out.print("primeNumbers");
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true}
        });
    }*/

    /*@Parameterized.Parameters
    public static Collection primeNumbers2() {
        // TODO:有两个@Parameterized.Parameters标记时测试函数时，不知道时哪个生效。
        // primeNumbers##teamcity[suiteTreeStarted name='|[0|]' locationHint='java:suite://com.example.hades.tdd.junit.api.test_parameters.TestParameterized.|[0|]']
        System.out.print("primeNumbers2");
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true}
        });
    }*/

    // If mark func primeNumbers(), ERROR:java.lang.Exception: No public static parameters method on class com.example.hades.tdd.junit.api.test_parameters.TestParameterized
    /*@Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true}
        });
    }*/

    // ERROR: java.lang.IllegalArgumentException: wrong number of arguments
    /*@Parameterized.Parameters
    public static Collection primeNumbers(int a) {
        return Arrays.asList(new Object[][]{
                {2, true},
                {6, false},
                {19, true},
                {22, false},
                {23, true}
        });
    }*/

    // This moreReadableAndTypeable will run 4 times since we have 5 parameters defined
    @Test
    public void testPrimeNumberChecker() {
        boolean isPrime = primeNumberChecker.validate(inputNumber);
        Assert.assertEquals(expectedResult, isPrime);
        System.out.println("check " + inputNumber + (isPrime ? " is prime." : " is not prime ."));
    }
}
