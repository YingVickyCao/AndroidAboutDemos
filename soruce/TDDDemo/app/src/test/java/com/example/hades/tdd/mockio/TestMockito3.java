package com.example.hades.tdd.mockio;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestMockito3 {
    @Mock
    Person person;

    @Test
    public void test2() throws Exception {
        Assert.assertNotNull(person);
    }
}