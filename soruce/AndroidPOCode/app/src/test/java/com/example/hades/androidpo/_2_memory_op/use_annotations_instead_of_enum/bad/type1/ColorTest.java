package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.type1;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.normal.Color;
import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.normal.TestCase;

import org.junit.Test;

public class ColorTest {
    @Test
    public void test() {
        new TestCase().test();
    }

    @Test
    public void test2() {
        // 类型安全
//        Color color = new Color();
        Color colorType = Color.BLUE;
        System.out.println(colorType.name());

        // 类型安全
        colorType.setType("B");
        System.out.println(colorType.name());  // BLUE

        System.out.println(colorType.getType()); // B
        colorType.setType(Color.GREEN.name());
        System.out.println(colorType.name()); // BLUE
    }
}