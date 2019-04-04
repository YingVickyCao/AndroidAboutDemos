package com.example.hades.dagger2._13_created_twice_when_live_together.live_together;

import com.example.hades.dagger2._13_created_twice_when_live_together.B;
import com.example.hades.dagger2._13_created_twice_when_live_together.dagger1.Dagger1ActivityModule;
import com.example.hades.dagger2._13_created_twice_when_live_together.dagger2.DaggerDagger2ActivityComponent;
import dagger.ObjectGraph;

import javax.inject.Inject;
import javax.inject.Inject1;

/*
    Dagger1 AModule@1989780873,A@1480010240
    Dagger1 BModule@81628611,B@1828972342,A@1480010240

    Dagger2 AModule@1867083167,A@1915910607
    Dagger2 BModule@284720968,B@1
 */
public class LiveTogetherActivity {
//    @Inject
//    @Inject1
//    B mB;

    @Inject1
    @Inject
    B mB;

    public void onCreate() {
        /*

            Dagger1 AModule@284720968,A@189568618
            Dagger1 BModule@793589513,B@1313922862,A@189568618

            Dagger2 AModule@41359092,A@149928006
            Dagger2 BModule@713338599,B@168423058,A@149928006

            B@168423058,A@149928006
         */
//        injectDagger1();
//        injectDagger2();

         /*
            Dagger2 AModule@41359092,A@149928006
            Dagger2 BModule@713338599,B@168423058,A@149928006

            Dagger1 AModule@284720968,A@189568618
            Dagger1 BModule@793589513,B@1313922862,A@189568618

            B@1313922862,A@189568618
         */
        injectDagger2();
        injectDagger1();

        System.out.println(mB.getInfo());
    }


    private void injectDagger1() {
        //Dagger 1
        final ObjectGraph objectGraph = ObjectGraph.create(new Dagger1ActivityModule());
        objectGraph.inject(this);
    }

    private void injectDagger2() {
        // Dagger2
        DaggerDagger2ActivityComponent.builder().build().inject(this);
    }
}
