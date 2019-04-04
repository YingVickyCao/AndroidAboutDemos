package com.example.hades.dagger2._13_created_twice_when_live_together.dagger1;

import com.example.hades.dagger2._13_created_twice_when_live_together.live_together.LiveTogetherActivity;
import dagger.Module1;

@Module1(injects = {Dagger1Activity.class, LiveTogetherActivity.class}
        , includes = {BModule.class}
        , complete = false
        , library = true)
public class Dagger1ActivityModule {
}