package com.example.hades.tdd.junit.test_execution_order;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.DEFAULT)
public class TestMethodOrder2 {

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