package com.hades.example.android.string_stringbuffer_stringbuilder.qa;

public class StringQA {


    public void sample1() {
        String str1 = "reeves";
        String str2 = "reeves";
        System.out.println(str1 == str2); // true
    }

    public void sample2() {
        String str1 = "reeves";
        String str2 = "ree" + "ves";
        System.out.println(str1 == str2); // true
    }

    public void sample3() {
        String s = new String("reeves");
    }

    public void sample4() {
        String str1 = "reeves";
        String str2 = str1.intern();
        System.out.println(str1 == str2); //true
    }

    public void sample5() {
        String str = "";
        str += "hello";
    }


    public void sample6() {
        String a = "hello2";
        String b = "hello" + 2;
        System.out.println((a == b)); // true
    }

    public void sample7() {
        String a = "hello2";
        String b = "hello";
        String c = b + 2;
        System.out.println((a == c)); // false
    }

    public void sample8() {
        String a = "hello2";
        final String b = "hello";
        String c = b + 2;
        System.out.println((a == c)); // true
    }

    public void sample9() {
        String a = "hello2";
        final String b = getHello();
        String c = b + 2;
        System.out.println((a == c)); // false
    }

    private String getHello() {
        return "hello";
    }

    public void sample10() {
        String str1 = "I";
        //str1 += "love"+"java";        1)
        str1 = str1 + "love" + "java";      //2)
    }
}
