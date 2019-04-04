package com.example.hades.tdd.mockio;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArgumentMatchers {
    @Test
    public void testMatch() throws Exception {
        Person person = mock(Person.class);
        when(person.eat(anyString())).thenReturn("Noodle");
        System.out.println(person.eat("Price"));
    }

    @Test
    public void test2() throws Exception {
        ArrayList mockedList = mock(ArrayList.class);
        when(mockedList.get(anyInt())).thenReturn("element");

        System.out.println(mockedList.get(1000));
        verify(mockedList).get(anyInt());
    }

    @Test
    public void testMatch2() throws Exception {
        Person person = mock(Person.class);
        // 自定义规则：当输入为偶数时，返回"Noodle"
        when(person.eat(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.length() * 2 == 0;
            }
        }))).thenReturn("Noodle");
        System.out.println(person.eat("Price"));
    }
}