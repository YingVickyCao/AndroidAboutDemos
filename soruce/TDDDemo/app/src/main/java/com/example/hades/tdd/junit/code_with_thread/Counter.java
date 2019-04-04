package com.example.hades.tdd.junit.code_with_thread;

public class Counter {
    public void count(int max) {
        System.out.println("=====================>");
        for (int i = 0; i < max; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
        System.out.println("<=====================");
    }

    public void countInThread(final int max) {
        System.out.println("=====================>");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("loop,start");
                for (int i = 0; i < max; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
                System.out.println("loop,end");

            }
        }).start();
        System.out.println("<=====================");
    }
}