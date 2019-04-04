package com.example.hades.tdd.junit.test_execution_order;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class TestMethodOrder3 {

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