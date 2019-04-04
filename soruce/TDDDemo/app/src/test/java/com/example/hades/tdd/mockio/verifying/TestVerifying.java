package com.example.hades.tdd.mockio.verifying;

import com.example.hades.tdd.mockio.Person;

import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestVerifying {

    @Test
    public void test() throws Exception {
        ArrayList mockList = mock(ArrayList.class);

        mockList.add("A");

        mockList.add("B");
        mockList.add("B");

        mockList.add("C");
        mockList.add("C");
        mockList.add("C");

        // ERROR:Argument(s) are different! Wanted:
//        verify(mockList).add("One");

        verify(mockList).add("A");
        verify(mockList, times(1)).add("A");

        verify(mockList, times(2)).add("B");
        verify(mockList, times(3)).add("C");

        verify(mockList, never()).add("D");

        verify(mockList, atLeastOnce()).add("C");
        verify(mockList, atLeast(2)).add("C");
        verify(mockList, atMost(5)).add("C");
    }


    @Test
    public void testVerify() throws Exception {
        Person person = mock(Person.class);
        when(person.getName()).thenReturn("A");
        System.out.println(person.getName() + "," + System.currentTimeMillis());

        // 延时1000ms 验证方法
        verify(person, after(1000)).getName();
        System.out.println(person.getName() + "," + System.currentTimeMillis());

        verify(person, atLeast(2)).getName();
        System.out.println(person.getName() + "," + System.currentTimeMillis());
    }

    @Test
    public void testVerify2() throws Exception {
        Person person = mock(Person.class);
        person.getName();
//        person.getName();
        // 验证方法至少调用2次
        verify(person, atLeast(2)).getName(); // ERROR:org.mockito.exceptions.verification.TooLittleActualInvocations:
    }

    @Test
    public void testVerify3() throws Exception {
        Person person = mock(Person.class);
        person.getName();
        person.getName();
        // 验证方法最多调用2次
        verify(person, atMost(2)).getName();
    }

    @Test
    public void testVerify4() throws Exception {
        Person person = mock(Person.class);
        person.getName();
//        person.getName();
        // 验证方法在100ms 之内调用2次
        verify(person, timeout(100).times(2)).getName(); // ERROR:org.mockito.exceptions.verification.TooLittleActualInvocations:
    }

}