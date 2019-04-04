package com.example.hades.tdd.mockio;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class TestOrder {
    @Test
    public void test() throws Exception {
        Person person = mock(Person.class);
        Person person2 = mock(Person.class);

        person.setName("A");
        person2.setName("B");

        InOrder inOrder = Mockito.inOrder(person, person2);
        // 执行顺序正确
        inOrder.verify(person).setName("A");
        inOrder.verify(person2).setName("B");

        // 执行顺序错误
        // ERROR:org.mockito.exceptions.verification.VerificationInOrderFailure:
        inOrder.verify(person2).setName("B");
        inOrder.verify(person).setName("A");
    }
}
