package com.example.hades.tdd.junit.test_runners.theories;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestTheories1 {
    @DataPoint
    public static String nameValue1 = "Tony";

    @DataPoint
    public static String nameValue2 = "Jim";

    @DataPoint
    public static int ageValue1 = 10;

    /**
     Result:
     Tony's age is 10
     Jim's age is 10
     */
//    @DataPoint
//    public static int ageValue2 = 20;

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
