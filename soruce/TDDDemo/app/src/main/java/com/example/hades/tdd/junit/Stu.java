package com.example.hades.tdd.junit;

public class Stu {
    String name;
    int id;

    public Stu(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Stu stu = (Stu) obj;
        if (id != stu.id) {
            return false;
        }

        if ((null == name && null != stu.name) || (null != name && null == stu.name)) {
            return false;
        }

        if (null == name && null == stu.name) {
            return true;
        }
        return name.equalsIgnoreCase(stu.name);
    }

//    @Override
//    public int hashCode() {
//        return (name == null) ? id : (id + name.hashCode());
//    }
}
