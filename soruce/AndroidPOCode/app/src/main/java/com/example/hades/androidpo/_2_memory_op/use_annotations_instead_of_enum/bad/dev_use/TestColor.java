package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.dev_use;

/**
 * Create by Vicky on 03/09/2018.
 **/
public class TestColor {
    public void test() {
//        Color color = new Color();

        // null
        Color color = Color.getColorType("A");

        // Color.Red
        Color color2 = Color.getColorType(Color.RED.name());
    }
}
