package com.example.hades.tdd.junit.assumptions_with_assume;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

public class TestAssumptions {
    @Test
    public void testAssumptions() {
        int i = 10;
        //假设进入testAssumptions时，变量i的值为10，如果该假设不满足，程序不会执行assumeThat后面的语句
        assumeThat(i, is(10));
        //如果之前的假设成立，会打印"assumption is true!"到控制台，否则直接调出，执行下一个测试用例函数
        System.out.println("assumption is true!");
    }
}
