package com.example.hades.dagger2._common._qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Documented
@Retention(RUNTIME)
public @interface Second {
    /**
     * The name.
     */
    String value() default "";
}