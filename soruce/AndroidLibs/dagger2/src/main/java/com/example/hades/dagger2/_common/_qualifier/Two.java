package com.example.hades.dagger2._common._qualifier;

import javax.inject.Qualifier;
import javax.inject.Qualifier1;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier1
@Qualifier
@Target({FIELD, PARAMETER, METHOD})
@Documented
@Retention(RUNTIME)
public @interface Two {
}
