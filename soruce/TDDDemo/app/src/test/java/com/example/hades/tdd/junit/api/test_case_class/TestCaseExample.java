package com.example.hades.tdd.junit.api.test_case_class;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestCaseExample extends TestCase {
    protected double fValue1;
    protected double fValue2;

    @Before
    public void setUp() {
        fValue1 = 2.0;
        fValue2 = 3.0;
    }

    @Test
    public void testAdd() {
        //count the number of moreReadableAndTypeable cases
        System.out.println("No of Test Case = " + this.countTestCases());

        //moreReadableAndTypeable getName
        String name = this.getName();
        System.out.println("Test Case Name = " + name);

        //moreReadableAndTypeable setName
        this.setName("testNewAdd");
        String newName = this.getName();
        System.out.println("Updated Test Case Name = " + newName);
        System.out.println(fValue1 + "," + fValue2);

        /**
         * TODO:String toString() 返回测试案例的一个字符串表示
         * testNewAdd(com.example.hades.tdd.junit.api.test_case_class.TestCaseExample)
         */
        System.out.println(this.toString());
    }

    //after used to close the connection or clean up activities
    public void tearDown() {
    }
}