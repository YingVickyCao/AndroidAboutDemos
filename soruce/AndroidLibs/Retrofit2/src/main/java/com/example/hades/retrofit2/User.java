package com.example.hades.retrofit2;

public class User {
    private String login;
    private String url;
    private String html_url;

    public User(String login, String url, String html_url) {
        this.login = login;
        this.url = url;
        this.html_url = html_url;
    }

    @Override
    public String toString() {
        return "{" +
                "login='" + login + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                '}';
    }
}