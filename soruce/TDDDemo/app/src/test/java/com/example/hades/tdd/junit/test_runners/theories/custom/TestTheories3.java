package com.example.hades.tdd.junit.test_runners.theories.custom;

import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestTheories3 {

    /**
     * Result:
     * Tony's age is 10
     * Tony's age is 20
     * Jim's age is 10
     * Jim's age is 20
     */
    @Theory
    public void testMethod(@ParametersSuppliedBy(NameSupplier.class) String name, @ParametersSuppliedBy(AgeSupplier.class) int age) {
        System.out.println(String.format("%s's age is %s", name, age));
    }

    @Theory
    public void multiplyIsInverseOfDivideWithInlineDataPoints(@TestedOn(ints = {0, 5, 10}) int amount, @TestedOn(ints = {0, 1, 2}) int m) {
        System.out.println(String.format("%s's amount is %s", amount, m));
    }
}