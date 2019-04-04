package com.example.hades.androidpo._2_memory_op.memory_leak.non_static_inner_class.good2;

class Inner {
    Foo foo;

    public void setFoo(Foo foo) {
        this.foo = foo;
    }

    void stuff() {
        foo.doStuff(10);
    }
}
