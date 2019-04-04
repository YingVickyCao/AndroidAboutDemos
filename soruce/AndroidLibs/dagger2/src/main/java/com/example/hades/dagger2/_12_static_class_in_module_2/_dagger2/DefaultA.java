package com.example.hades.dagger2._12_static_class_in_module_2._dagger2;

import com.example.hades.dagger2._common._qualifier.One;
import com.example.hades.dagger2._common._qualifier.Two;

import javax.inject.Inject;

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
        CustomNumber integer1;
        CustomNumber integer2;

        @Inject
        public Info(@One CustomNumber integer1, @Two CustomNumber integer2) {
            this.integer1 = integer1;
            this.integer2 = integer2;
        }
    }
}
