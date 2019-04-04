package com.example.hades.togetherdagger1anddagger2;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Qualifier1;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Qualifier1
@Documented
@Retention(RUNTIME)
public @interface First {
    /**
     * The name.
     */
    String value() default "";
}
