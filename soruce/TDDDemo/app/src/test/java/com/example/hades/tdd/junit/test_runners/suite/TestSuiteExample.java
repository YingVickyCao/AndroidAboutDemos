package com.example.hades.tdd.junit.test_runners.suite;

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class TestSuiteExample extends TestSuite {
    public static void main(String[] a) {
        // add the moreReadableAndTypeable's in the suite
        TestSuite suite = new TestSuite(TestJunit1.class, TestJunit2.class);
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println("Number of moreReadableAndTypeable cases = " + result.runCount());
    }
}