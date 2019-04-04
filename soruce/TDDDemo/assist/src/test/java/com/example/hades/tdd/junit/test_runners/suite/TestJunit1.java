package com.example.hades.tdd.junit.test_runners.suite;

import com.example.hades.tdd.assist.MessageUtil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit1 {

    private String message = "Robert";
    private MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        assertEquals(message, messageUtil.printMessage());
    }
}
