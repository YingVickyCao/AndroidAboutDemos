package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good2;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants;

// 类型安全
public class Color {
    private String mType;

    //这是一个私有的构造器，意味着在这个类的外部没有办法去实例化一个该类的对象。
    // 类型安全
    private Color(String color) {
        this.mType = color;
    }

    public String getType() {
        return mType;
    }

//    public void setType(String type) {
//        mType = type;
//    }

    public void setType(Color type) {
        if (null != type) {
            mType = type.getType();
        } else {
            mType = null;
        }
    }

    // 实例化该类的对象，供外部类使用。
    public static final Color RED = new Color(ColorConstants.RED);
    public static final Color GREEN = new Color(ColorConstants.GREEN);
    public static final Color YELLOW = new Color(ColorConstants.YELLOW);
    public static final Color BLUE = new Color(ColorConstants.BLUE);
}