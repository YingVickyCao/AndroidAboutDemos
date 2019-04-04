package com.example.hades.tdd.junit.test_runners;


import com.example.hades.tdd.junit.test_runners.suite.TestJunit1;
import com.example.hades.tdd.junit.test_runners.suite.TestJunit2;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    // ERROR: Error: Could not find or load main class com.example.hades.tdd.junit.runner.TestRunner
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestJunit1.class, TestJunit2.class);
        for (Failure failure : result.getFailures()) {
            // testSalutationMessage(com.example.hades.tdd.junit.test_runners.suit.TestJunit2): expected:<[Hi!]Robert> but was:<[]Robert>
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}