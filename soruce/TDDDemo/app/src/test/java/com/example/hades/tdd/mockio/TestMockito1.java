package com.example.hades.tdd.mockio;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TestMockito1 {
    @Test
    public void test2() throws Exception {
        Person person = mock(Person.class);
        Assert.assertNotNull(person);
    }
}