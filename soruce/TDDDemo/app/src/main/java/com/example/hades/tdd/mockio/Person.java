package com.example.hades.tdd.mockio;

import android.util.Log;

import java.util.List;

public class Person {
    private static final String TAG = Person.class.getSimpleName();
    String name;
    int id;
    B b;

    public Person() {
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getName2() {
        return "Name";
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void print() {
//        System.out.println(toString());
        Log.d(TAG, "print()");
    }

    public void print(String s) {
//        System.out.println(s);
        Log.d(TAG, "print(),s=" + s);
    }

    public String eat(String food) {
//        return name + " is eating " + food;
        return getName() + " is eating " + food;
    }

    void requestData(final List<Integer> integers) {
    }

    A getA() {
        return findA(0);
    }

    A findA(int pos) {
        return new A();
    }

    public Integer getSize() {
        Integer num = getNum();
        if (null == num) {
            return null;
        }
        return num;
    }

    public Integer getNum() {
        // Mock Android code
//        System.out.println("getNum," + b.getNum());
//        Log.d(TAG, "getNum" + b.getNum());
        return b.getNum();
//        return getB().getIntegers(); // getB() = b
    }


    public B getB() {
        return b;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
