package com.example.hades.tdd.junit.test_execution_order;

import org.junit.Test;

public class TestMethodOrder1 {

    @Test
    public void testA() {
        System.out.println("A");
    }

    @Test
    public void testB() {
        System.out.println("B");
    }

    @Test
    public void testC() {
        System.out.println("C");
    }
}