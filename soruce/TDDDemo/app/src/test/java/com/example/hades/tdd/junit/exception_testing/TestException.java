package com.example.hades.tdd.junit.exception_testing;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class TestException {
    /**
     * ERROR:
     * java.lang.Exception: Unexpected exception, expected<java.lang.ArithmeticException> but was<java.lang.AssertionError>
     */
    @Test(expected = ArithmeticException.class)
    public void test1() {
        int num = 5;
        Assert.assertTrue(num > 6);
    }

    /**
     * ERROR:
     * java.lang.AssertionError: Expected exception: java.lang.ArithmeticException
     */
    @Test(expected = ArithmeticException.class)
    public void test2() {
        int num = 5;
        Assert.assertFalse(num > 6);
    }

    @Test(expected = ArithmeticException.class)
    public void test3() {
        int num = 5;
        Assert.assertFalse(num > 6);
        throw new ArithmeticException();
    }

    /**
     * ERROR:java.lang.IndexOutOfBoundsException: Index: 2, Size: 0
     */
    @Test
    public void testIndexOutOfBoundsException() {
        new ArrayList<Integer>().get(2);
    }

    /**
     * 方式1：
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void empty() {
//        new ArrayList<Integer>().get(0);
        new ArrayList<Integer>().get(2);
    }

    /**
     * 方式2：JUnit 3.x - Try/Catch Idiom
     * To address this you can use the try/catch idiom which prevailed in JUnit 3.x:
     */
    @Test
    public void testExceptionMessage() {
        System.out.println("start");
        try {
            System.out.println("before get");
            new ArrayList<Integer>().get(2);
            System.out.println("after get");
            Assert.fail("Expected an IndexOutOfBoundsException to be thrown");
            System.out.println("end");
        } catch (IndexOutOfBoundsException ex) {
//            Assert.assertThat(ex.getMessage(), is("Index: 3, Size: 0"));
            Assert.assertThat(ex.getMessage(), is("Index: 2, Size: 0"));
        }
    }

    /**
     * 方式3：ExpectedException Rule
     * 规定抛出异常类型和异常信息：
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * java.lang.AssertionError:
     * Expected: (an instance of java.lang.IndexOutOfBoundsException and exception with message a string containing "Index: 3, Size: 0")
     * but: exception with message a string containing "Index: 3, Size: 0" message was "Index: 2, Size: 0"
     * Stacktrace was: java.lang.IndexOutOfBoundsException: Index: 2, Size: 0
     *
     * @throws IndexOutOfBoundsException
     */
    @Test
    public void shouldTestExceptionMessage() throws IndexOutOfBoundsException {
        System.out.println("start");
        List<Integer> list = new ArrayList<Integer>();
        thrown.expect(IndexOutOfBoundsException.class);
//        thrown.expectMessage("Index: 3, Size: 0");
        thrown.expectMessage("Index: 2, Size: 0");
        System.out.println("before get");
        list.get(2); // Stacktrace was: java.lang.IndexOutOfBoundsException: Index: 2, Size: 0
        System.out.println("after get");
        System.out.println("end");
    }
}