package com.example.hades.retrofit2;

public class ResponseIssue extends Issue {
    private String state;

    public ResponseIssue() {
        this.state = state;
    }

    @Override
    public String toString() {
        return "{" +
                "state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
