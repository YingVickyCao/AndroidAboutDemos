package com.example.hades.tdd.junit.run;

import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit moreReadableAndTypeable, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**
 * 如果一个 TestCase 失败了，会继续运行吗？
 * 如果其中一个 TestCase失败了，其他 Testcase 会继续运行吗？
 */
public class TestFail {
    @Test
    public void add() throws Exception {
        System.out.println("@Test");
        System.out.println("before assert");
        /**
         * ERROR:java.lang.RuntimeException: Method d in android.util.Log not mocked. See http://g.co/androidstudio/not-mocked for details.
         Log.d(TAG, "before assert");
         */
        // ERROR:java.lang.AssertionError: Values should be different. Actual: 5
        Assert.assertNotEquals(5, 2 + 3);
        System.out.println("after assert");
    }

    @Test
    public void add2() throws Exception {
        System.out.println("@Test2");
    }
}