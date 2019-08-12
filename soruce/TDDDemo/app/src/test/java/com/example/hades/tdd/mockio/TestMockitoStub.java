package com.example.hades.tdd.mockio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestMockitoStub {
    /**
     * doReturn: 希望方法返回返回值
     */
    @Test
    public void test_thenReturn() throws Exception {
        /**
         * 使用Mockito或者PowerMockito，mock出来的类，如果不使用thenReturn设置返回值，只能返回0或者null；
         * 若使用类方法原本返回的值，使用thenCallRealMethod或者doCallRealMethod
         */
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

    /**
     * doReturn: 希望方法抛出异常
     */
    @Test
    public void test_thenThrow() throws Exception {
        Person person = mock(Person.class);
        System.out.println(person.getId());
        when(person.getId()).thenThrow(new NullPointerException("Invalid id"));
        System.out.println(person.getId());// ERROR: java.lang.NullPointerException: Invalid id
    }

    /**
     * Answer=智能打桩:根据输入而不是固定数据给出返回值。
     * thenAnswer：非空方法打桩
     * doAnswer: 空方法打桩
     **/
    @Test
    public void test_thenAnswer() throws Exception {
        Person person = mock(Person.class);
        when(person.eat(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return args[0].toString() + " is delicious";
            }
        });

        System.out.println(person.eat("Price")); // Price is delicious
        System.out.println(new Person("A", 1001).eat("Apple")); // A is eating Apple
    }

    /*
       1.Mock 方法有返回值
       对象= mock (类名.class);
       when (对象.方法 (参数)).thenReturn (方法的返回值);


       2. Mock 方法没有返回值
       类名 对象 = Mockito.mock(类名.class);
        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "called with arguments: " + args;
            }
        }).when(对象).方法名();
     */

    @Test
    public void test_doAnswer_mockVoid() {
        Person person = mock(Person.class);
        person.print();// debug 发现，big 没有实际运行print()，而是被拦截了。

        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "called with arguments: " + args;
            }
        }).when(person).print("A");
        person.print("A");
        // mock前后并没有影响
    }

    @Test
    public void test2_doAnswer_mockVoid() {
        Person person = mock(Person.class);
        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "called with arguments: " + args;
            }
        }).when(person).print(); // void print()
        person.print();
    }


    @Test
    public void test3_doAnswer_mockVoid() {
        Person person = mock(Person.class);
        person.print("A");
        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "called with arguments: " + args;
            }
        }).when(person).print(anyString()); // void print(String s)
        person.eat2("A");
    }

    // doNothing = 希望某个方法不做任何事情。在void返回方法或者方法中使用，或者与正在执行的单元测试无关。
    @Test
    public void test3_doNothing() {
        Person person = mock(Person.class);
        Mockito.doNothing().when(person).updateRequestActionAndApproval(new ArrayList<Integer>());
    }

    @Test
    public void test_henCallRealMethod() throws Exception {
        /*
        Mock for Person, hashCode: 1336996537
        null
        Name
        Mock for Person, hashCode: 1336996537
         */
        Person person = mock(Person.class);
        System.out.println(person);
        System.out.println(person.getName2());

        when(person.getName2()).thenCallRealMethod();
        System.out.println(person.getName2());
        System.out.println(person);
    }

    @Test
    public void test2_thenCallRealMethod() throws Exception {
        Person person = mock(Person.class);

        when(person.getName()).thenReturn("A"); // In eat, When "name" -> null. When  "getName" -> "A"
        when(person.eat(anyString())).thenCallRealMethod();
        System.out.println(person.eat("Noodles"));

        when(person.findA(0)).thenReturn(new A());
//        when(person.check(null, false)).thenReturn(new A());
        when(person.check(null, false)).thenCallRealMethod();
        A a = person.check(null, false);
        System.out.println(a);
        Assert.assertNotNull(a);
    }

    @Test
    public void test_doCallRealMethod() throws Exception {
        /*
        Mock for Person, hashCode: 1336996537
        null
        Name
        Mock for Person, hashCode: 1336996537
         */
        Person person = mock(Person.class);
        System.out.println(person);
        System.out.println(person.getName2());

        doCallRealMethod().when(person).getName2();
        System.out.println(person.getName2());
        System.out.println(person);
    }

    @Test
    public void test2_doCallRealMethod() throws Exception {
        Person person = mock(Person.class);
        person.setName("A");
        System.out.println(person.getName());

        doCallRealMethod().when(person).setName("A");
        person.setName("A");
        System.out.println(person.getName());
    }

    @Test
    public void testDoReturn() throws Exception {
        Person person = mock(Person.class);
        Mockito.doReturn(11).when(person).getId();
        System.out.println(person.getId());

        /**
         * ERROR:org.mockito.exceptions.misusing.CannotStubVoidMethodWithReturnValue:
         'setName' is a *void method* and it *cannot* be stubbed with a *return value*!
         */
//        Mockito.doReturn("A").when(person).setName("A");
//        System.out.println(person.getName());

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