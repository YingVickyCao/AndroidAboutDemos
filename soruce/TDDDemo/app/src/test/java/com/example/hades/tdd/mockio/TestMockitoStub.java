package com.example.hades.tdd.mockio;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

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
    /**
     * doReturn: 希望方法返回返回值
     */
    @Test
    public void test_thenReturn() throws Exception {
        /**
         * 使用Mockito或者PowerMockito，mock出来的类，如果不使用thenReturn设置返回值，只能返回0或者null；
         * 使用thenCallRealMethod或者doCallRealMethod，调用真实api。
         * thenCallRealMethod 与doCallRealMethod 用处相同，但语法不同。
         */
        {
            Person person = mock(Person.class);
            Assert.assertNull(person.getName());
        }

        {
            Person person = mock(Person.class);
            when(person.getName()).thenReturn("A");
            Assert.assertEquals("A", person.getName()); // A
            Assert.assertEquals("A", person.getName()); // A
            /**
             * mock后一直存在，因此，为了避免mocked值影响以后mock，应该使用单独函数/{mock n}测试每一种情况。
             */
        }
        {
            Person person = mock(Person.class);
            when(person.getName()).thenReturn("A");
            Assert.assertEquals("A", person.getName()); // A
            when(person.getName()).thenReturn("B");
        }
        {
            Person person = mock(Person.class);
            person.setName("C");// nothing will be set.
            Assert.assertNull(person.getName());
        }

        {
            /**
             * thenReturn and thenAnswer?
             * 不执行方法体，直接返回person.getNum()的值。因此，b=null也没有关系
             */
            Person mock = mock(Person.class);
            when(mock.getNum()).thenReturn(null);
            Assert.assertNull(mock.getName()); // null
        }

        {

            Person mock = mock(Person.class);
            when(mock.getNum()).thenReturn(1);
            /**
             * mock出来的类，如果不使用thenReturn设置返回值，只能返回0或者null；
             * when(mock.getNum()).thenReturn(1) ：  对mock.getSize()的return结果没有影响，仅仅影响mock.getNum()的执行，使其 not NullPointerException。
             */
            Assert.assertEquals(Integer.valueOf(0), mock.getSize()); // 0
        }
        {
            Person person2 = mock(Person.class);
            when(person2.getSize()).thenReturn(1);
            Assert.assertEquals(Integer.valueOf(1), person2.getSize()); // 1
        }
    }

    /**
     * doReturn: 希望方法抛出异常
     */
    @Test
    public void test_thenThrow() throws Exception {
        try {
            Person person = mock(Person.class);
            when(person.getId()).thenThrow(new NullPointerException("Invalid id"));
            person.getId();// ERROR: java.lang.NullPointerException: Invalid id
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Answer=智能打桩:根据输入而不是固定数据给出返回值。
     * thenAnswer：非空方法打桩
     * doAnswer: 空方法打桩
     **/
    @Test
    public void test_thenAnswer() throws Exception {
        {
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

        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "called with arguments: " + args;
            }
        }).when(person).setName("A");
        person.setName("A");
        System.out.println(person.getName()); // null
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

    // doNothing = 希望某个方法不做任何事情。在void返回方法或者方法中使用，或者与正在执行的单元测试无关。
    @Test
    public void doNothing() {
        Person person = mock(Person.class);
        Mockito.doNothing().when(person).requestData(new ArrayList<Integer>());
    }

    @Test
    public void thenCallRealMethod() throws Exception {
        {
            Person person = mock(Person.class);
            Assert.assertNotNull(person);
            Assert.assertNull(person.getName2());
        }

        {
            Person person = mock(Person.class);
            Assert.assertNotNull(person);
            when(person.getName2()).thenCallRealMethod();
            Assert.assertEquals("Name", person.getName2());
        }

        {
            Person person = mock(Person.class);
            when(person.getName()).thenReturn("A"); // In eat, When "name" -> null. When  "getName" -> "A"
            when(person.eat(anyString())).thenCallRealMethod();
            System.out.println(person.eat("Noodles")); // A is eating Noodles
        }

        {
            Person mock = mock(Person.class);
            /**
             * doCallRealMethod/thenCallRealMethod:调用真实api。
             * 相当：在调用person2.getSize()时，将 getNum()直接替换成1.
             */
            when(mock.getNum()).thenReturn(1);
            when(mock.getSize()).thenCallRealMethod();
            Assert.assertEquals(Integer.valueOf(1), mock.getSize()); // 1
        }

        {
            Person mock = mock(Person.class);
            when(mock.findA(0)).thenReturn(new A());
            when(mock.getA()).thenCallRealMethod();
            Assert.assertNotNull(mock.getA()); // Not null
        }

        {
            Person mock = mock(Person.class);
            when(mock.findA(5)).thenReturn(new A());
            when(mock.getA()).thenCallRealMethod();
            Assert.assertNull(mock.getA()); // null
        }
    }

    @Test
    public void test_doCallRealMethod() throws Exception {
        {
            Person person = mock(Person.class);
            Assert.assertNotNull(person);
            Assert.assertNull(person.getName2());
        }
        {
            Person person = mock(Person.class);
            doCallRealMethod().when(person).getName2();
            Assert.assertNotNull(person);
            Assert.assertEquals("Name", person.getName2());
        }
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
        try {
            Person person = mock(Person.class);
            Mockito.doThrow(new RuntimeException("Invalid id ")).when(person).setId(1000000);
            person.setId(1000000); // ERROR:java.lang.RuntimeException: Invalid id
        } catch (RuntimeException ex) {
            System.out.println(ex);
        }
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