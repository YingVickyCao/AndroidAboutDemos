package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.normal;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants;

/*
    编译
    javac Color.java

    反编译
    javap -c  Color.class > Color.class.txt
    javap  Color.class > Color.class_2.txt
 */
public enum Color {
    RED(ColorConstants.RED), GREEN(ColorConstants.GREEN), YELLOW(ColorConstants.YELLOW), BLUE(ColorConstants.BLUE);

    private String mType;

    Color(String name) {
        mType = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}