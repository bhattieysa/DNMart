package com.example.dn_mart;

public class HistoryModel {

    private String o_id,name,date,price,id,status;

    public HistoryModel (String o_id,String name,String price,String status,String date) {
        this.o_id = o_id;
        this.name = name;
        this.price= price;
        this.status = status;
        this.date= date;

    }

    public String getO_id() {

        return o_id;
    }

    public String getStatus() {

        return status;
    }

    public String getDate() {

        return date;
    }


    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }
}




