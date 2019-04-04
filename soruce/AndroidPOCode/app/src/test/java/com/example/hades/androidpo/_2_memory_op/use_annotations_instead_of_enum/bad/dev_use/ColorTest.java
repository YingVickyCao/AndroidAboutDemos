package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.dev_use;

import org.junit.Test;

public class ColorTest {
    @Test
    public void test() {
        // 类型安全
        Color color = Color.getColorType("A");
        System.out.println(color); // null

        // 类型安全
        Color color2 = Color.getColorType(Color.BLUE.getType());
        System.out.println(color2.getType());

        // 类型安全
        Color colorType3 = Color.BLUE;
        System.out.println(colorType3.getType());

        colorType3.setType("B");
        System.out.println(colorType3.getType()); // B
    }
}