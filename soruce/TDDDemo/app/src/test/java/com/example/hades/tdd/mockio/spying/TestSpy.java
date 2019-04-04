package com.example.hades.tdd.mockio.spying;

import com.example.hades.tdd.mockio.Person;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class TestSpy {
    @Test
    public void notSpy() throws Exception {
        Person person = mock(Person.class);
        System.out.println(person.getName());
        person.setName("A");// MockMethodInterceptor
        System.out.println(person.getName());
    }

    // You can create spies of real objects. When you use the spy then the real methods are called (unless a method was stubbed).
    @Test
    public void hasSpy() throws Exception {
        Person person = spy(Person.class);
        System.out.println(person.getName());
        person.setName("A");// MockMethodInterceptor
        System.out.println(person.getName());
    }
}