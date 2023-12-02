package com.marcos.android1_project2;

public class DataClass {


    public DataClass(String dataName, String dataPhone, String dataMail) {
        this.dataName = dataName;
        this.dataPhone = dataPhone;
        this.dataMail = dataMail;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataPhone() {
        return dataPhone;
    }

    public String getDataMail() {
        return dataMail;
    }

    private String dataName;
    private String dataPhone;
    private String dataMail;

}
