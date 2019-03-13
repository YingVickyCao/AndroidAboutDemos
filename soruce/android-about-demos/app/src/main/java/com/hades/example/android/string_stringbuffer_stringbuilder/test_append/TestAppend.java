package com.hades.example.android.string_stringbuffer_stringbuilder.test_append;

public class TestAppend {
    /*
     String +, time=2484
     StringBuffer append, time=7
     StringBuilder append, time=6
     */
    public void test() {
        long startTime = System.currentTimeMillis();
        testStringAppend();

        long endTime = System.currentTimeMillis();
        System.out.println("String +, time=" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        testStringBufferAppend();
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer append, time=" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        testStringBuilderAppend();
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder append, time=" + (endTime - startTime));
    }

    private void testStringAppend() {
        String str = new String();
        for (int i = 0; i < 10000; i++) {
            str += "hello";
        }
    }

    private void testStringBufferAppend() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            str.append("hello");
        }
    }

    private void testStringBuilderAppend() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            str.append("hello");
        }
    }
}
