package com.example.hades.dagger2._14_not_support_static_injectons.dagger1;

import com.example.hades.dagger2._14_not_support_static_injectons.IAnimal;
import dagger.Module1;
import dagger.ObjectGraph;

import javax.inject.Inject1;
import java.util.ArrayList;
import java.util.List;

public class Dagger1Activity {
    @Inject1
    IAnimal mAnimal;

    public void onCreate() {
        //Dagger 1
        final ObjectGraph objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);

        System.out.println(mAnimal.getName());
    }

    public boolean isCat() {
        return true;
    }

    List<Object> getModules() {
        List<Object> list = new ArrayList<Object>();
        list.add((isCat() ? CatModule.class : DogModule.class));
        list.add(new Dagger1ActivityModule());
        return list;
    }

    @Module1(injects = {Dagger1Activity.class}
//            , includes = {}
            , complete = false
            , library = true)
    public class Dagger1ActivityModule {
    }
}