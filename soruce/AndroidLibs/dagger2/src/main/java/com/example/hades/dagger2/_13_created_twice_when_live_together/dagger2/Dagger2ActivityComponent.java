package com.example.hades.dagger2._13_created_twice_when_live_together.dagger2;

import com.example.hades.dagger2._13_created_twice_when_live_together.live_together.LiveTogetherActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {Dagger2ActivityModule.class})
public interface Dagger2ActivityComponent {
    void inject(Dagger2Activity activity);

    void inject(LiveTogetherActivity activity);
}