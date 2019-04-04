package com.example.hades.tdd.mockio;

public class Home {
    private Person person;

    public Home(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }
}
