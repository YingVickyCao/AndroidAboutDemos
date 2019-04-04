package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.good;

// javac Foo.java
// javap Foo.class > Foo.class.txt
public class Foo {
    static class Inner {
        void stuff() {
            // ERROR:Foo.this can not be referenced from a static context
//            Foo.this.doStuff(Foo.this.mValue);
        }
    }

    int mValue;

    public void run() {
        Inner in = new Inner();
        in.stuff();
    }

    void doStuff(int value) {
        mValue = value;
    }
}