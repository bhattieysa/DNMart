package com.example.dn_mart;

public class ShopModel {


    private String image,name,id;

    public ShopModel(String id,String name,String image) {
        this.image = image;
        this.name = name;
        this.id= id;





    }

    public String getImage() {

        return image;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}




