package com.example.hades.dagger2._8_provider_2._dagger1;

import javax.inject.Inject1;
import javax.inject.Provider1;

public class Subject {
    private final Provider1<ICustomBuilder> customBuilderProvides1;

    public static Subject create(final Info info) {
        return new Subject(info);
    }

    private Subject(final Info info) {
        customBuilderProvides1 = info.customBuilderProvides1;
    }

    public String setNum(int num) {
        ICustomBuilder builder = customBuilderProvides1.get();
        builder.setNum(num);
        return "ICustomBuilder@" + builder.hashCode() + ":" + builder.asString();
    }

    public static final class Info {
        @Inject1
        Provider1<ICustomBuilder> customBuilderProvides1;
    }
}