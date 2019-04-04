package com.example.hades.tdd.junit.test_runners.category;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SlowTests.class)
@Categories.ExcludeCategory(FastTests.class)
@Suite.SuiteClasses({A.class, B.class}) // Note that Categories is a kind of Suite
public class TestCategory2 {
    // Will run A.b, but not A.a or B.c
}