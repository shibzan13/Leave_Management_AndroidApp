package com.example.myapp;

public class DataClass1 {

    private String dataDesc,dataName;
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
    public DataClass1(String dataName,String dataDesc) {
        this.dataName=dataName;

        this.dataDesc = dataDesc;
    }
    public DataClass1(){
    }
}