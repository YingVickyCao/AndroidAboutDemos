package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.type2;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good.Color;
import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good.TestCase;

import org.junit.Test;

public class ColorTest {
    @Test
    public void test1() throws Exception {
        new TestCase().test();
    }

    @Test
    public void test2() throws Exception {
        // 这个比较特殊，会失去类型安全的特性
//                Color color = new Color();
        Color color = new Color("A");
        System.out.println(color.getType()); //  A

        color = new Color(Color.GREEN);
        System.out.println(color.getType());

//        color.setType("A");
        color.setType(Color.RED);
        System.out.println(color.getType());
    }

}