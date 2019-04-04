package com.example.hades.tdd.junit.test_runners.theories;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestTheories2 {
    @DataPoints
    public static String[] names = {"Tony", "Jim"};

    @DataPoints
    public static int[] ageValue1 = {10, 20};

    /**
     * Result:
     * Tony's age is 10
     * Tony's age is 20
     * Jim's age is 10
     * Jim's age is 20
     */
    @Theory
    public void testMethod(String name, int age) {
        System.out.println(String.format("%s's age is %s", name, age));
    }
}