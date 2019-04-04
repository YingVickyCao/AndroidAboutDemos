package com.example.hades.tdd.mockio;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class TestMockito4 {
    @Mock
    Person person;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void test2() throws Exception {
        Assert.assertNotNull(person);
    }
}