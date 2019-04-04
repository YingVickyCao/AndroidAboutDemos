package com.example.hades.tdd.junit.test_runners.suite;

import com.example.hades.tdd.assist.MessageUtil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestJunit2 {
    private String message = "Robert";
    private MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testSalutationMessage() {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Robert";
        assertEquals(message, messageUtil.salutationMessage());
    }
}
