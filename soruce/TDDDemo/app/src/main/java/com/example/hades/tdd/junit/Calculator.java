package com.example.hades.tdd.junit;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int add() {
        return add(a, b);
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int multiply() {
        return multiply(a, b);
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void clear() {
        this.a = a;
        this.b = b;
    }

    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Calculator() {
    }

    private int a;
    private int b;
}
