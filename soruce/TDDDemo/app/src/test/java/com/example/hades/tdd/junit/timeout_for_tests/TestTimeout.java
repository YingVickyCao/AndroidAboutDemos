package com.example.hades.tdd.junit.timeout_for_tests;

import org.junit.Test;

public class TestTimeout {
    @Test(timeout = 1000)
    public void add1() throws Exception {
        System.out.println("@@Test(timeout = 1000) ======>");
        System.out.println("@@Test(timeout = 1000) <======");
    }

    @Test(timeout = 2000)
    public void add2() throws Exception {
        System.out.println("@@Test(timeout = 1000) ======>");
        Thread.sleep(5000);// ERROR: org.junit.runners.model.TestTimedOutException: moreReadableAndTypeable timed out after 1000 milliseconds
        System.out.println("@@Test(timeout = 1000) <======");
    }
}