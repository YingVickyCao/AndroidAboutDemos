package com.example.hades.tdd.junit.lifecycle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Lifecycle for One moreReadableAndTypeable class.
 */
public class TestLifecycle {
    private static final String TAG = TestLifecycle.class.getSimpleName();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass1");
    }

    @BeforeClass
    public static void beforeClass2() {
        System.out.println("@BeforeClass2");
    }

    @Before
    public void before2() throws Exception {
        System.out.println("@Before2");
    }

    @Before
    public void before() throws Exception {
        System.out.println("@Before1");
    }

    @After
    public void after2() throws Exception {
        System.out.println("@After2");
    }

    @After
    public void after() throws Exception {
        System.out.println("@After1");
    }

    // void add() throws Exception =>ERROR:java.lang.Exception: Method add() should be public
    // public void add(int a) throws Exception => ERROR：java.lang.Exception: Method add should have no parameters
    // public static void add() throws Exception => ERROR:java.lang.Exception: Method add() should not be static
    @Test
    public void add() throws Exception {
        System.out.println("@Test");
    }

    @Test
    public void add2() throws Exception {
        System.out.println("@Test2");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("@AfterClass1");
    }

    @AfterClass
    public static void afterClass2() {
        System.out.println("@AfterClass2");
    }
}