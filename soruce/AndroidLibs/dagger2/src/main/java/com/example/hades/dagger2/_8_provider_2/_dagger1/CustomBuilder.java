package com.example.hades.dagger2._8_provider_2._dagger1;

public final class CustomBuilder implements ICustomBuilder {
    public int num;

    public static CustomBuilder create() {
        return new CustomBuilder();
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String asString() {
        return String.valueOf(num);
    }
}
