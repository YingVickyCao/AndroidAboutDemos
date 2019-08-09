package com.example.hades.tdd.mockio;

public class Person {
    String name;
    int id;

    public Person() {
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
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
        System.out.println(toString());
    }

    public void print(String s) {
        System.out.println(s);
    }

    public String eat(String food) {
        return name + " is eating " + food;
//        return getName() + " is eating " + food;
    }

    public String eat2(String food) {
        String s = name + " is eating " + food;
        print(s);
        return s;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
