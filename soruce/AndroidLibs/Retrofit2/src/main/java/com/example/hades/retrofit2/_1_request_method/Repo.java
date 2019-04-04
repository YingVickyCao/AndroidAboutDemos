package com.example.hades.retrofit2._1_request_method;

public class Repo implements Comparable<Repo> {
    private Long id;
    private String name;

    public Repo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (null != obj) {
            if (obj instanceof Repo) {
                Repo tmp = (Repo) obj;
                return ((tmp.id == this.id) && (null != name && name.equals(tmp.name)));
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Repo o) {
//        return desc(o);
        return asc(o);
    }


    private int asc(Repo o) {
        if (null == o.getId()) {
            return 1;
        }

        if (null == this.getId()) {
            return -1;
        }

        return this.id.compareTo(o.id);
    }

    private int desc(Repo o) {
        if (null == this.id) {
            return 1;
        }
        if (null == o.getId()) {
            return -1;
        }
        return o.id.compareTo(this.id);
    }
}