package com.example.dn_mart;

public class InvoiceModel {

    private String product_name,quantity,price,product_id,amount;

    public InvoiceModel(String product_name,String quantity,String price,String product_id, String amount) {
        this.product_id = product_id;
        this.product_name = product_name;

        this.quantity = quantity;
        this.amount= amount;

        this.price = price;



    }

    public String getProduct_name() {

        return product_name;
    }

    public String getProduct_id() {

        return product_id;
    }



    public String getAmount() {
        return amount;
    }

    public String getQuantity() {
        return quantity;
    }
    public String getPrice() {
        return price;
    }
}




