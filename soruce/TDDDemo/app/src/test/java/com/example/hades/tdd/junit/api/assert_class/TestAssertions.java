package com.example.hades.tdd.junit.api.assert_class;

import com.example.hades.tdd.junit.Calculator;
import com.example.hades.tdd.junit.Stu;

import org.junit.Assert;
import org.junit.Test;


// https://www.w3cschool.cn/junit/jycq1hus.html
public class TestAssertions {
    @Test
    public void testAssertEquals() {
        String str1 = new String("abc");
        String str2 = new String("abc");
        String str3 = null;
        String str4 = "abc";
        String str5 = "abc";

        // LONG_START
        Assert.assertEquals(1000L, 1000L);
        // LONG_END


        // DOUBLE_START
        Assert.assertEquals(0.12d, 2.12d, 2);
        /**
         * ERROR:java.lang.AssertionError:
         Expected :0.12
         Actual   :5.12
         */
//        Assert.assertEquals(0.12d, 5.12d, 0);
        // DOUBLE_END


        // FLOAT_START
        Assert.assertEquals(0.12f, 2.12f, 2);
        /**
         ERROR:java.lang.AssertionError: 0.12f != 2.12f
         Expected :0.12
         Actual   :2.1
         */
//        Assert.assertEquals("0.12f != 2.12f", 0.12f, 2.12f, 0);
        /**
         * ERROR:java.lang.AssertionError:
         Expected :0.12
         Actual   :5.12
         */
//        Assert.assertEquals(0.12f, 5.12f, 0);
        // FLOAT_END


        // OBJECT_START
        Assert.assertEquals(str1, str2);
        /**
         * ERROR:java.lang.AssertionError:
         Expected :abc
         Actual   :null
         */
//        Assert.assertEquals(str1, str3);
        Assert.assertEquals(str1, str4);
        Assert.assertEquals(str4, str5);

        Assert.assertEquals(new Stu("A", 10), new Stu("A", 10));
        Assert.assertEquals(new Stu(null, 10), new Stu(null, 10));

        Assert.assertNotEquals(new Stu("A", 10), new Stu("A", 20));
        Assert.assertNotEquals(new Stu("A", 10), new Stu("B", 10));
        Assert.assertNotEquals(new Stu("A", 10), new Stu("B", 20));
        Assert.assertNotEquals(new Stu(null, 10), new Stu("A", 10));
        Assert.assertNotEquals(new Stu("A", 10), new Stu(null, 20));
        Assert.assertNotEquals(new Stu(null, 10), new Stu(null, 20));

        /**
         *ERROR:java.lang.AssertionError:
         Expected :com.example.hades.tdd.junit.Calculator@573fd745
         Actual   :com.example.hades.tdd.junit.Calculator@15327b79
         */
//        Assert.assertEquals(new Calculator(), new Calculator());
        Assert.assertNotEquals(new Calculator(), new Calculator());
        // OBJECT_END
    }

    @Test
    public void testAssertTrue() {
        int val1 = 5;
        int val2 = 6;

        Assert.assertTrue(val1 < val2);
        Assert.assertFalse(val1 > val2);
    }

    @Test
    public void testAssertNull() {
        String str1 = new String("abc");
        String str2 = null;

        Assert.assertNotNull(str1);
        Assert.assertNull(str2);
    }

    /**
     * 检查两个相关对象是否指向同一个对象
     **/

    /**
     * str2 hashcode=96354,str5 hashcode=96354
     * Calculator1 hashcode=1463801669,Calculator2 hashcode=355629945
     * stu1 hashcode=75,str2 hashcode=75
     */
    @Test
    public void testAssertSame() {
        String str2 = new String("abc");
        String str3 = null;

        String str4 = "abc";
        String str5 = "abc";

        String[] expectedArray = {"one", "two", "three"};
        String[] resultArray = {"one", "two", "three"};

        Assert.assertSame(str4, str5);
        Assert.assertNotSame(str2, str5);
        System.out.println("str2 hashcode=" + str2.hashCode() + "," + "str5 hashcode=" + str5.hashCode());
        Assert.assertNotSame(str5, str3);
        Assert.assertNotSame(expectedArray, resultArray);

        Calculator c1 = new Calculator();
        Calculator c2 = new Calculator();
        Assert.assertNotSame(c1, c2);
        System.out.println("c1 hashcode=" + c1.hashCode() + "," + "c2 hashcode=" + c2.hashCode());

        Stu stu1 = new Stu("A", 10);
        Stu stu2 = new Stu("A", 10);
        Assert.assertNotSame(stu1, stu2);
        System.out.println("stu1 hashcode=" + stu1.hashCode() + "," + "str2 hashcode=" + stu2.hashCode());
        System.out.println("stu1 vS stu2 hashCode=" + (stu1.hashCode() == stu2.hashCode()));
        System.out.println("stu1 vS stu2 ==" + (stu1 == stu2));
    }

    //Check whether two arrays are equal to each other.
    @Test
    public void testAssertArrayEquals() {
        String[] expectedArray = {"one", "two", "three"};
        String[] resultArray = {"one", "two", "three"};
        Assert.assertArrayEquals(expectedArray, resultArray);


        Assert.assertArrayEquals(expectedArray, resultArray);

        Stu[] list1 = getStuList();
        Stu[] list2 = getStuList();
        Assert.assertArrayEquals(list1, list2);

        Calculator[] c1 = getCalculatorList();
        Calculator[] c2 = getCalculatorList();
        /**
         * ERROR:arrays first differed at element [0];
         Expected :com.example.hades.tdd.junit.Calculator@32a1bec0
         Actual   :com.example.hades.tdd.junit.Calculator@22927a81
         */
        Assert.assertArrayEquals(c1, c2);
    }

    private Stu[] getStuList() {
        return new Stu[]{new Stu("A", 10), new Stu("B", 20)};
    }

    private Calculator[] getCalculatorList() {
        return new Calculator[]{new Calculator(), new Calculator()};
    }

    @Test
    public void testFail() {
        /**
         * ERROR:
         * java.lang.AssertionError: invoke fail()
         */
        Assert.fail("invoke fail()");
        int num = 5;
        Assert.assertFalse(num > 6);
    }
}

