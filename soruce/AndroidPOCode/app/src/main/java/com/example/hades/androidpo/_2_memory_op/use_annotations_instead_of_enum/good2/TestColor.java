package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good2;

/**
 * Create by Vicky on 03/09/2018.
 **/
public class TestColor {
    public void test() {
        // Type is safe
//        Color color = new Color();
        Color color2 = Color.BLUE;
        Color color3 = Color.RED;
        color3.setType(color2);

        // Type is not safe
//        color3.setType("A");
    }
}
