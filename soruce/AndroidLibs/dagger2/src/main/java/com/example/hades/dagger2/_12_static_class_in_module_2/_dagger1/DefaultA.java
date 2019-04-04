package com.example.hades.dagger2._12_static_class_in_module_2._dagger1;

import javax.inject.Inject1;
import javax.inject.Named1;

public class DefaultA implements IA {
    private final CustomNumber mNum1;
    private final CustomNumber mNum2;

    public DefaultA(final Info info) {
        mNum1 = info.integer1;
        mNum2 = info.integer2;
    }

    public CustomNumber getNumber1() {
        return mNum1;
    }

    public CustomNumber getNumber2() {
        return mNum2;
    }

    public static final class Info {
        @Inject1
        @Named1(NumsNames.ONE)
        CustomNumber integer1;

        @Inject1
        @Named1(NumsNames.TWO)
        CustomNumber integer2;
    }
}
