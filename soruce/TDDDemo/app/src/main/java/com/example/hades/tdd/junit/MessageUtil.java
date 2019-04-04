package com.example.hades.tdd.junit;

public class MessageUtil {
    public String salutationMessage() {
        return "Hi!" + message;
    }

    public String print() {
        System.out.println(message);
        return message;
    }

    public MessageUtil(String message) {
        this.message = message;
    }

    private String message;
}
