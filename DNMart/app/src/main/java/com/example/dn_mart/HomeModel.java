package com.example.dn_mart;

public class HomeModel {

    private String image,name,discount,quantity,des,price,id,flag;

        public HomeModel(String image,String name,String discount,String quantity,String des,String price,String id,String flag) {
            this.image = image;
            this.name = name;
            this.discount= discount;
            this.quantity = quantity;
            this.des= des;
            this.flag=flag;

            this.price = price;
            this.id = id;


        }

        public String getImage() {

            return image;
        }

    public String getFlag() {

        return flag;
    }

    public String getID() {

        return id;
    }

    public String getDiscount() {
        return discount;
    }
    public String getName() {
        return name;
    }
    public String getDes() {
        return des;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getPrice() {
        return price;
    }
    }




