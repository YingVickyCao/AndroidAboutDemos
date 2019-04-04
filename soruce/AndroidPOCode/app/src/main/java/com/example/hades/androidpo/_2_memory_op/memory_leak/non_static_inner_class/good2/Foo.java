package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.good2;

// javac Foo.java
// javap Foo.class > Foo.class.txt
public class Foo {
    int mValue;

    public void run() {
        Inner in = new Inner();
        in.setFoo(this);
        in.stuff();
        in.setFoo(null);
    }

    void doStuff(int value) {
        this.mValue = value;
    }
}