package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.normal;

public class TestCase {
    public void test() {
        // Type is safe.
//        Color color = new Color();
        Color colorType = Color.BLUE;
        colorType.setType(Color.GREEN.name());

        //  Type no safe
        colorType.setType("B");
    }
}
