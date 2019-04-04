package com.example.hades.tdd.junit.api.test_suite_class;

import com.example.hades.tdd.junit.test_runners.suite.TestJunit1;
import com.example.hades.tdd.junit.test_runners.suite.TestJunit2;

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class TestSuiteExample extends TestSuite {
    /**
     * ERROR:Error: Could not find or load main class com.example.hades.tdd.junit.api.test_suite_class.TestSuiteExample
     * 需要命令行运行
     */
    public static void main(String[] a) {
        // add the test's in the suite
        TestSuite suite = new TestSuite(TestJunit1.class, TestJunit2.class);
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println("Number of test cases = " + result.runCount());
    }
}