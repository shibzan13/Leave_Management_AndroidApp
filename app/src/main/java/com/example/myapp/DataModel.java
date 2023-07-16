package com.example.myapp;

public class DataModel {
    private String dataImage;
    private String dataName;
    private String dataDesc;

    public DataModel() {
        // Empty constructor needed for Firebase
    }

    public DataModel(String dataImage, String dataName, String dataDesc) {
        this.dataImage = dataImage;
        this.dataName = dataName;
        this.dataDesc = dataDesc;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataDesc() {
        return dataDesc;
    }
}
