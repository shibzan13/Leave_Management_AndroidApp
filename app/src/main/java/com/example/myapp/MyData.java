package com.example.myapp;

public class MyData {
    private String dataName;
    private String dataImage;
    private String dataDesc;

    public MyData(String dataName, String dataImage, String dataDesc) {
        this.dataName = dataName;
        this.dataImage = dataImage;
        this.dataDesc = dataDesc;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getDataDesc() {
        return dataDesc;
    }
}
