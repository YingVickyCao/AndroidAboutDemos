package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.bad;

// cd /Users/hades/Documents/Gitee/AnroidPO/source/AndroidPOCode/app/src/main/java/com/example/hades/androidpo/_2_memory_op/memory_leak/non_static_inner_class/bad
// javac Foo.java
// javap Foo.class > Foo.class.txt
// javap Foo$Inner.class > Foo$Inner.class.txt
public class Foo {
    int mValue;

    class Inner {
        void stuff() {
            Foo.this.doStuff(10);
        }
    }

    public void run() {
        Inner in = new Inner();
        in.stuff();
    }

    private void doStuff(int value) {
        mValue = value;
    }
}