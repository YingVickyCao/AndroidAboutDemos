package com.example.hades.tdd.mockio;

import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

public class TestInject {
    @Spy
    Person person;

    @InjectMocks
    Home home;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    /**
     * null
     * A
     */
    @org.junit.Test
    public void test() throws Exception {
        System.out.println(home.getName());

        when(person.getName()).thenReturn("A");
        System.out.println(home.getName());
    }
}