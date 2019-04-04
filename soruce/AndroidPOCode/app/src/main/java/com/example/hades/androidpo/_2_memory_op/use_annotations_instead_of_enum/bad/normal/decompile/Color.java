package com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.bad.normal.decompile;

import com.example.hades.androidpo._2_memory_op.use_annotations_instead_of_enum.ColorConstants;

public final class Color {
    //public final class Color extends Enum<Color> {
    public static final Color RED;

    public static final Color GREEN;

    public static final Color BLUE;

    public static final Color YELLOW;

    public static Color[] VALUES;

    private String mType;


    /**
     * Sole constructor.  Programmers cannot invoke this constructor.
     * It is for use by code emitted by the compiler in response to
     * enum type declarations.
     *
     * @param name    - The name of this enum constant, which is the identifier
     *                used to declare it.
     * @param ordinal - The ordinal of this enumeration constant (its position
     *                in the enum declaration, where the initial constant is assigned
     */
    private Color(String name, int ordinal) {
//        super(name, ordinal);
    }

    public static Color[] values() {
        Color[] tmp = new Color[VALUES.length];
        System.arraycopy(VALUES, 0, tmp, 0, VALUES.length);
        return tmp;
    }

    public static Color valueOf(String name) {
        switch (name) {
            case ColorConstants.RED:
                return RED;

            case ColorConstants.GREEN:
                return GREEN;


            case ColorConstants.BLUE:
                return BLUE;

            case ColorConstants.YELLOW:
                return YELLOW;

            default:
                throw new IllegalArgumentException("Unkonw");

        }
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    static {
        RED = new Color(ColorConstants.RED, 0);

        GREEN = new Color(ColorConstants.GREEN, 1);

        BLUE = new Color(ColorConstants.BLUE, 2);

        YELLOW = new Color(ColorConstants.YELLOW, 3);

        VALUES = new Color[]{RED, GREEN, BLUE, YELLOW};
    }
}