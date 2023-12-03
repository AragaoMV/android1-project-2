package com.marcos.android1_project2;

public class DataClass {

    private String addName;
    private String addPhone;
    private String addMail;

    public String getDataName() {
        return addName;
    }

    public String getDataPhone() {
        return addPhone;
    }

    public String getDataMail() {
        return addMail;
    }

    public DataClass(String addName, String addPhone, String addMail) {
        this.addName = addName;
        this.addPhone = addPhone;
        this.addMail = addMail;

    }
}
