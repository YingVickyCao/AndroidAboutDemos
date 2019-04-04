package com.example.hades.retrofit2;

public class Issue {
    protected String title;
    protected String body;
    protected String[] assignees;
    protected int milestone;
    protected String[] labels;


    public Issue() {
        title = "Found a bug";
        body = "problem";
        assignees = new String[]{"YingVickyCao"};
        milestone = 1;
        labels = new String[]{"bug"};
    }

    @Override
    public String toString() {
        return "Issue{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
