package com.example.hades.androidpo.temp._4_lazy_load;

import java.util.ArrayList;
import java.util.List;

public class LazyLoad {
    //bad
    List<String> list = new ArrayList<>();

    //good
//    private List<String> list;

    public void good(boolean flag) {
        if (flag) {
            String str = "abc";
            if (null == list) {
                list = new ArrayList<>();
            }
            list.add(str);
            print(str);
        }
    }

    public void bad(boolean flag) {
        String str = "abc";

        if (flag) {
            list.add(str);
            print(str);
        }
    }

    private void print(String data) {
        System.out.println(data);
    }
}
