package com.example.hades.dagger2._12_static_class_in_module_2._live_together;

import com.example.hades.dagger2._common._qualifier.One;
import com.example.hades.dagger2._common._qualifier.Two;

import javax.inject.Inject;
import javax.inject.Inject1;

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
        @Inject
        @One
//        @Named("ONE") // ERROR:
//        @Named1("ONE")// ERROR:
                CustomNumber integer1;

        @Inject
        @Inject1
        @Two
        CustomNumber integer2;

        @Inject
        @Inject1
        public Info(@One CustomNumber integer1, @Two CustomNumber integer2) {
            this.integer1 = integer1;
            this.integer2 = integer2;
        }

    }
}
