package com.hades.example.android.string_stringbuffer_stringbuilder.parse;

/**
 * https://www.cnblogs.com/yahokuma/p/3677576.html
 */
public class TestString2 {
    public void test() {
        String str1 = new String("abc");
        String str2 = "abc";
        // 中间创建的对象：1个StringBuffer ， 2 个 String
        String str3 = "ab" + new String("c");
    }
}