package com.example.hades.androidpo._2_memory_op.memory_leak;

import java.io.IOException;
import java.io.InputStream;

public class TestFile {
    public void test() {
        InputStream stream = null;
        try {
//            stream =/
            //.....
        } catch (Exception ex) {

        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stream = null;
            }
        }
    }
}
