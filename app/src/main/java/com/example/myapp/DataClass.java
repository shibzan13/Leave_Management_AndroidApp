package com.example.myapp;

public class DataClass {

    private String dataDesc,dataName,dataId;
    private String dataImage;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDataName() {
        return dataName;
    }
    public String getDataDesc() {
        return dataDesc;
    }
    public String getDataImage() {
        return dataImage;
    }
    public DataClass(String dataName,String dataDesc, String dataImage) {
        this.dataName=dataName;

        this.dataDesc = dataDesc;

        this.dataImage = dataImage;
    }
    public DataClass(){
    }
}