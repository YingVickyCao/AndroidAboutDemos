package com.example.hades.tdd.mockio.subbing;

import com.example.hades.tdd.mockio.Person;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class TestDoSubbingFamilyMethods {

    // When spying real objects and calling real methods on a spy brings side effects
    @Test
    public void test_doReturn() throws Exception {
        List list = new ArrayList();

        List spyList = spy(ArrayList.class);

        // ERROR:// IndexOutOfBoundsException
//        when(spyList.get(0)).thenReturn("A");
        doReturn('B').when(spyList).get(1);
        System.out.println(spyList.get(1));
    }

    // When spying real objects and calling real methods on a spy brings side effects
    @Test
    public void test_doReturn_multi() throws Exception {
        List list = new ArrayList();
        List spyList = spy(ArrayList.class);

        // ERROR:IndexOutOfBoundsException
//        when(spyList.get(1)).thenReturn("A", "A2", "A3");
        doReturn('A', "A2", "A3").when(spyList).get(1);

        System.out.println(spyList.get(1));
    }

    // Overriding a previous exception-stubbing:Note that the scenarios are very rare, though.
    @Test
    public void test_doReturn_2() throws Exception {
        Person person = mock(Person.class);

        when(person.getId()).thenThrow(new RuntimeException());
        // ERROR:RuntimeException
//        when(person.getId()).thenReturn(99);

        doReturn(99).when(person).getId();
        System.out.println(person.getId());
    }

    // Overriding a previous exception-stubbing:Note that the scenarios are very rare, though.
    @Test
    public void test_doReturn_2_multi() throws Exception {
        Person person = mock(Person.class);

        when(person.getId()).thenThrow(new RuntimeException());
        // ERROR:RuntimeException
//        when(person.getId()).thenReturn(99,10,20);

        doReturn(99, 10, 20).when(person).getId();
        System.out.println(person.getId());
    }

    @Test
    public void test_doThrow() throws Exception {
        Person person = mock(Person.class);

        person.setId(9);
        System.out.println(person.getId());

        doThrow(new RuntimeException("Invalid id ")).when(person).setId(1000000);
        // ERROR:java.lang.RuntimeException: Invalid id
//        person.setId(1000000);
        System.out.println(person.getId());
    }

    @Test
    public void test_doCallRealMethod() throws Exception {
        Person person = mock(Person.class);
        person.setName("A");
        person.print();

        // TODO: 02/05/2018 not work
        doCallRealMethod().when(person).setName("B");
        doCallRealMethod().when(person).print();
        person.setName("B");
        person.print();
    }

    @Test
    public void test_doAnswer() throws Exception {
        Person mocked = mock(Person.class);
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return args[0].toString() + " test doAnswer";
            }
        }).when(mocked).eat("Price");

        mocked.eat("Price");
    }

    @Test
    public void test_doNothing() throws Exception {
        Person mocked = mock(Person.class);
        // ERROR: MockitoException, Only void methods can doNothing()!
        doNothing()
                .doThrow(new RuntimeException())
                .when(mocked).print();

        //does nothing the first time:
        mocked.print();

        //throws RuntimeException the next time:
//        mocked.print();
    }

    @Test
    public void test_doNothing_2() throws Exception {
        List list = new ArrayList();
        List spy = spy(list);

        //let's make clear() do nothing
        doNothing().when(spy).clear();

        spy.add("one");

        //clear() does nothing, so the list still contains "one"
        spy.clear();
    }
}