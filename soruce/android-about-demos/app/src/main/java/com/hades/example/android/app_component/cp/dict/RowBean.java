package com.hades.example.android.app_component.cp.dict;

public class RowBean {
    private long id = -1;
    private String col2;
    private int col3;

    public RowBean(long id, String col2, int col3) {
        this.id = id;
        this.col2 = col2;
        this.col3 = col3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public int getCol3() {
        return col3;
    }

    public void setCol3(int col3) {
        this.col3 = col3;
    }
}
