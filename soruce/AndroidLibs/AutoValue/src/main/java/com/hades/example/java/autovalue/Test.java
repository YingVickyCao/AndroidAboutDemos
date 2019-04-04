package com.hades.example.java.autovalue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Test {
    public static void main(String[] args) {
//        System.out.println("Test dev environment");

        new Test().testAnimal();
    }

    public void testAnimal() {
        Animal dog = Animal.create("dog", 4);
        assertEquals("dog", dog.name());
        assertEquals(4, dog.numberOfLegs());

        // You probably don't need to write assertions like these; just illustrating.
        assertEquals(Animal.create("dog", 4), dog);
        assertNotEquals(Animal.create("cat", 4), dog);
        assertNotEquals(Animal.create("dog", 2), dog);

        assertEquals("Animal{name=dog, numberOfLegs=4}", dog.toString());
    }
}