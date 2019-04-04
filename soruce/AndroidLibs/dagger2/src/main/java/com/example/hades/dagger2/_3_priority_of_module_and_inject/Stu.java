package com.example.hades.dagger2._3_priority_of_module_and_inject;

import javax.inject.Inject;

public class Stu {
    private String name;

    public Stu(String name) {
        this.name = name;
    }

    @Inject
    public Stu() {
        this.name = "provided by @Inejct Constructor";
    }

    public String getName() {
        return name;
    }
}
