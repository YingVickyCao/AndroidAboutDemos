package com.hades.example.android.app_component.cp.system.media;

public class MediaInfo {
    private String name;
    private String desc;
    private String fileName;

    public MediaInfo(String name, String desc, String fileName) {
        this.name = name;
        this.desc = desc;
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
