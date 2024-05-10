package com.example.dn_mart;

public class CartModel {

    private String image,name,id,price,discount,quantity,description,cartquantity;

    public CartModel(String id,String name,String image,String price,String discount,String quantity,String description, String cartquantity) {
        this.image = image;
        this.name = name;
        this.id= id;
        this.cartquantity=cartquantity;
        this.price=price;
        this.discount=discount;
        this.quantity=quantity;
        this.description=description;





    }

    public String getImage() {

        return image;
    }
    public String getcartquantity() {

        return cartquantity;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getDiscount() {
        return discount;
    }
    public String getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }


}









