package com.example.hades.tdd.mockio.subbing;

import com.example.hades.tdd.mockio.Person;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

public class TestSubbing {
    @Test
    public void subbing() throws Exception {
        /**
         null,0
         A
         A
         B
         */
        Person mocked = mock(Person.class);

        // default value
        System.out.println(mocked.getName() + "," + mocked.getId());

        // Once stubbed, the method will always return a stubbed value, regardless of how many times it is called.
        when(mocked.getName()).thenReturn("A");
        System.out.println(mocked.getName());
        System.out.println(mocked.getName());

        // Stubbing can be overridden
        when(mocked.getName()).thenReturn("B");
        System.out.println(mocked.getName());
    }


    @Test
    public void stubbingShorter() throws Exception {
        String price = "Price";

        Person mock = mock(Person.class);

        when(mock.eat(price)).thenReturn("Oranges", "Noodles");

        System.out.println(mock.eat(price));
    }

    @Test
    public void stubbingChaining() throws Exception {
        String price = "Price";
        String noodles = "Noodles";

        Person mock = mock(Person.class);

        when(mock.eat(price)).thenReturn("Oranges");
        System.out.println(mock.eat(price));

        when(mock.eat(price)).thenReturn("Noodles");
        System.out.println(mock.eat(price));
    }

    @Test
    public void stubbingConsecutiveCalls() throws Exception {
        String price = "Price";
        String noodles = "Noodles";

        Person mock = mock(Person.class);

        when(mock.eat(price))
                .thenThrow(new RuntimeException())
                .thenReturn(noodles);

        // ERROR:RuntimeException
        //First call: throws runtime exception:
        mock.eat(price);

        //Second call: prints "Noodles"
        System.out.println(mock.eat(price));

        //Any consecutive call: prints "Noodles" as well (last stubbing wins).
        System.out.println(mock.eat(price));
    }


    /***
     null
     A
     A
     B
     B
     */
    @Test
    public void testThenReturn() throws Exception {
        Person person = mock(Person.class);
        System.out.println(person.getName());  // null

        when(person.getName()).thenReturn("A");
        System.out.println(person.getName()); // A
        System.out.println(person.getName()); // A

        when(person.getName()).thenReturn("B");
        System.out.println(person.getName());//B

        person.setName("C");// nothing will be set.
        System.out.println(person.getName()); // Not "C", is "B"
    }

    @Test
    public void testThenThrow() throws Exception {
        Person person = mock(Person.class);
        System.out.println(person.getId());
        when(person.getId()).thenThrow(new NullPointerException("Invalid id"));
        System.out.println(person.getId());// ERROR: java.lang.NullPointerException: Invalid id
    }

    @Test
    public void test_stubbingWithCallbacks_thenAnswer() throws Exception {
        Person person = mock(Person.class);
//        person.print();// debug 发现，big 没有实际运行print()，而是被拦截了。
        when(person.eat(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return args[0].toString() + " is delicious";
            }
        });

        System.out.println(new Person("A", 1001).eat("Price"));
        System.out.println(person.eat("Price"));
    }

    @Test
    public void testThenCallRealMethod() throws Exception {
        Person person = mock(Person.class);
        System.out.println(person.eat("Noodles"));

        when(person.getName()).thenReturn("A"); // In eat, When "name" -> null. When  "getName" -> "A"
        when(person.eat(anyString())).thenCallRealMethod();
        System.out.println(person.eat("Noodles"));

        System.out.println(new Person("A", 1001).eat("Noodles"));
    }

    // reset() in the middle of the test method is a code smell (you're probably testing too much).
    @Test
    public void test_reset() throws Exception {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);
        System.out.println(mock.size());

        reset(mock);
        //at this point the mock forgot any interactions & stubbing
        System.out.println(mock.size());
    }


    // To create serializable mock use MockSettings.serializable():
    @Test
    public void serializableMocks() throws Exception {
        List<Object> list = new ArrayList<Object>();
        List<Object> spy = mock(ArrayList.class,
                withSettings()
                        .spiedInstance(list)
                        .defaultAnswer(CALLS_REAL_METHODS)
                        .serializable());
    }

}
