package com.example.hades.tdd.mockio;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestMockitoStub {

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
    public void testThenAnswer() throws Exception {
        Person person = mock(Person.class);
//        person.print();// debug 发现，big 没有实际运行print()，而是被拦截了。
        when(person.eat(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
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

    @Test
    public void testDoReturn() throws Exception {
        Person person = mock(Person.class);
        Mockito.doReturn(11).when(person).getId();
        System.out.println(person.getId());

        /**
         * ERROR:org.mockito.exceptions.misusing.WrongTypeOfReturnValue:
         Integer cannot be returned by getName()
         getName() should return String
         */
//        Mockito.doReturn(12).when(person).getName();
//        System.out.println(person.getId());
    }

    @Test
    public void testDoThrow() throws Exception {
        Person person = mock(Person.class);
        Mockito.doThrow(new RuntimeException("Invalid id ")).when(person).setId(1000000);
        person.setId(9);
        System.out.println(person.getId());
        person.setId(1000000); // ERROR:java.lang.RuntimeException: Invalid id
        System.out.println(person.getId());
    }

    @Test
    public void testDoCallRealMethod() throws Exception {
        Person person = mock(Person.class);
        person.setName("A");
        System.out.println(person.getName());


        /**
         * ERROR:org.mockito.exceptions.misusing.CannotStubVoidMethodWithReturnValue:
         'setName' is a *void method* and it *cannot* be stubbed with a *return value*!
         */
//        Mockito.doReturn("A").when(person).setName("A");
//        System.out.println(person.getName());

        doCallRealMethod().when(person).setName("A");
        person.setName("A");
        System.out.println(person.getName());

//        when(person.getName()).thenReturn("A"); // In eat, When "name" -> null. When  "getName" -> "A"
//        when(person.eat(anyString())).thenCallRealMethod();
//        System.out.println(person.eat("Noodles"));
//
//        System.out.println(new Person("A", 1001).eat("Noodles"));
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

    @Test
    public void testMatch() throws Exception {
        Person person = mock(Person.class);
        when(person.eat(anyString())).thenReturn("Noodle");
        System.out.println(person.eat("Price"));
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