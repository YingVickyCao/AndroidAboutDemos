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

    public String eat2(String food) {
        String s = name + " is eating " + food;
        print(s);
        return s;
    }

    // Mock Android code
    public A findA(int pos) {
        return new A();
    }

    public A check(String s, boolean flag) {
        if (null == s || s.isEmpty() || flag) {
            return findA(0);
        }
        return null;
    }

    void updateRequestActionAndApproval(final List<Integer> integers) {

    }

    public Integer getSize() {
        List<Integer> list = getIntegers();
        if (null == list) {
            return null;
        }
        return list.size();
    }

    public Integer getSize2() {
        List<Integer> list = getIntegers2();
        if (null == list) {
            return null;
        }
        return list.size();
    }

    public List<Integer> getIntegers() {
        return getB().getIntegers();
    }

    public List<Integer> getIntegers2() {
        return b.getIntegers();
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
