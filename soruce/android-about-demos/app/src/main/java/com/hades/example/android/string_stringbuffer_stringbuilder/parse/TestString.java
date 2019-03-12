package com.hades.example.android.string_stringbuffer_stringbuilder.parse;

public class TestString {
    public void test() {
        int a = 10;
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello");

        System.out.println(s1 == s2); // true
        System.out.println(s3 == s4); // false
        System.out.println(s1 == s3); // false
    }
}