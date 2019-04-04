package com.example.hades.dagger2._8_provider_2._live_together;

import javax.inject.Inject;
import javax.inject.Inject1;
import javax.inject.Provider;
import javax.inject.Provider1;

public class Subject {
    private final Provider1<ICustomBuilder> customBuilderProvides1;
    private final Provider<ICustomBuilder> customBuilderProvides2;

    public static Subject create(final Info info) {
        return new Subject(info);
    }

    private Subject(final Info info) {
        customBuilderProvides1 = info.customBuilderProvides1;
        customBuilderProvides2 = info.customBuilderProvides2;
    }

    public String setNum(int num) {
        ICustomBuilder builder = (null != customBuilderProvides1 ?
                customBuilderProvides1.get()
                : customBuilderProvides2.get());
        builder.setNum(num);
        return "ICustomBuilder@" + builder.hashCode() + ":" + builder.asString();
    }

    public static final class Info {
        @Inject1
        Provider1<ICustomBuilder> customBuilderProvides1;

        @Inject
        Provider<ICustomBuilder> customBuilderProvides2;

        @Inject1
        public Info(Provider1<ICustomBuilder> customBuilderProvides1) {
            this.customBuilderProvides1 = customBuilderProvides1;
        }

        @Inject
        public Info(Provider<ICustomBuilder> customBuilderProvides2) {
            this.customBuilderProvides2 = customBuilderProvides2;
        }
    }
}