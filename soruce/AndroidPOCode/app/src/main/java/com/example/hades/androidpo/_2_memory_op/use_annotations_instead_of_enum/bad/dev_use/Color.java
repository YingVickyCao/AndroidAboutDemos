package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.dev_use;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    RED("RED"), GREEN("GREEN"), YELLOW("YELLOW"), BLUE("BLUE");

    private String mType;

    Color(String color) {
        this.mType = color;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    @Override
    public String toString() {
        return mType;
    }

    private static Map<String, Color> mMap = new HashMap<>();

    static {
        mMap.put(RED.getType(), RED);
        mMap.put(GREEN.getType(), GREEN);
        mMap.put(YELLOW.getType(), YELLOW);
        mMap.put(BLUE.getType(), BLUE);
    }

    public static Color getColorType(String name) {
        return mMap.get(name);
    }
}