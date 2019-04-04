package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good;

import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.GREEN;
import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.RED;

public class TestCase {
    public void test() {
//      Color color = new Color();

//        Color color = new Color("A");

        Color color = new Color(GREEN);
        System.out.println(color.getType());

//        color.setType("A");
        color.setType(RED);
        System.out.println(color.getType());
    }
}
