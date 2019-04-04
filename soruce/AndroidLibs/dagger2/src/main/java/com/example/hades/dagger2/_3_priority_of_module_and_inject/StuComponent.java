package com.example.hades.dagger2._3_priority_of_module_and_inject;

import dagger.Component;

@Component(modules = StuModule.class)
public interface StuComponent {
    void inject(TestPriorityActivity activity);
}
