package com.example.hades.tdd.junit.timeout_for_tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TestTimeout2 {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000); // Two choice:millis or seconds

    @Test
    public void add1() throws Exception {
        System.out.println("@@Test(timeout = 1000) ======>");
        System.out.println("@@Test(timeout = 1000) <======");
    }

    @Test
    public void add2() throws Exception {
        System.out.println("@@Test(timeout = 1000) ======>");
        Thread.sleep(5000);// ERROR: org.junit.runners.model.TestTimedOutException: moreReadableAndTypeable timed out after 1000 milliseconds
        System.out.println("@@Test(timeout = 1000) <======");
    }
}