package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.good;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.BLUE;
import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.GREEN;
import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.RED;
import static com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants.YELLOW;

// 类型安全
public class Color {
    @Type
    String mType;

    @StringDef({RED, GREEN, YELLOW, BLUE}) // 限定值
    @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在.class 文件
    public @interface Type {//接口，定义新的注解类型
    }

    public @Type
    String getType() {
        return mType;
    }

    public void setType(@Type String type) {
        mType = type;
    }

    public Color(@Type String type) {
        mType = type;
    }
}