package com.example.hades.retrofit2._1_request_method._post;

public class LoginResult {
    private String msg;
    private String name;
    private int status;

    public LoginResult(String msg, String name, int status) {
        this.msg = msg;
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "msg='" + msg + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
