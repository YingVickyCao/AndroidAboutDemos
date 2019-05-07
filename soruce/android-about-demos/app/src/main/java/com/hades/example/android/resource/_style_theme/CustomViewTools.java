package com.hades.example.android.resource._style_theme;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

public class CustomViewTools {
    public void printTypedArray(String tag, TypedArray typedArray) {
        if (null == typedArray) {
            Log.d(tag, "printTypedArray: typedArray = null");
            return;
        }
        Log.d(tag, "printTypedArray,typedArray.getIndexCount()=" + typedArray.getIndexCount());
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int index = typedArray.getIndex(i);
            Log.e(tag, "printTypedArray ,index=" + index);
        }
    }

    public void printAttributeSet(String tag, AttributeSet attrs) {
        if (null == attrs) {
            Log.d(tag, "printAttributeSet, AttributeSet is null.");
            return;
        }

        int attributeCount = attrs.getAttributeCount();
        Log.i(tag, "printAttributeSet,当前属性个数为：" + attributeCount);
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = attrs.getAttributeName(i);

            if (attributeName.equals("style")) {
                String attributeValue = attrs.getAttributeValue(i);
                Log.d(tag, String.format("printAttributeSet,当前属性索引为：%d,索引名为：%s", i, attributeName) + ",当前属性值为：：" + attributeValue);
            } else {
                Log.d(tag, String.format("printAttributeSet, 当前属性索引为：%d,索引名为：%s", i, attributeName));
            }
        }
    }
}
