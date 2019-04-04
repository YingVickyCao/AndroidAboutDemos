package com.example.hades.tdd.junit.code_with_thread;

import net.jodah.concurrentunit.Waiter;

import org.junit.Test;

import java.util.concurrent.TimeoutException;

public class CounterTest {
    @Test
    public void count() throws Exception {
        new Counter().count(MAX);
    }


    @Test
    public void countInThread() throws Exception {
        new Counter().countInThread(MAX);
    }

    @Test
    public void countInThread2() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Counter().countInThread(MAX);
            }
        }).start();
    }

    @Test
    public void countInThread3() throws Exception {
        final Waiter waiter = new Waiter();
        new Counter().countInThread(MAX);
//        waiter.resume();
        waiter.await(0);
    }

    private final int MAX = 3;
}