package com.example.hades.tdd.mockio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestMockito2 {
    @Mock
    Person person;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test2() throws Exception {
        Assert.assertNotNull(person);
    }
}