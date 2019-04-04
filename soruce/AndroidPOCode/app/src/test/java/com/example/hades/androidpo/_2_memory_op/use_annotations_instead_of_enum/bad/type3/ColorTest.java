package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.type3;

import org.junit.Test;

public class ColorTest {
    @Test
    public void getType() throws Exception {
        // 类型不安全
        String color = Color.BLUE;
        System.out.println(color);
        String color2 = "A";
        System.out.println(color2);
    }
}