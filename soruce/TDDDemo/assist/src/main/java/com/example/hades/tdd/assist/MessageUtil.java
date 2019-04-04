package com.example.hades.tdd.assist;

/**
 * Created by hades on 13/04/2018.
 */

public class MessageUtil {
    public String salutationMessage() {
        return message;
    }

    public String printMessage() {
        System.out.println(message);
        return message;
    }

    public MessageUtil(String message) {
        this.message = message;
    }

    private String message;
}
