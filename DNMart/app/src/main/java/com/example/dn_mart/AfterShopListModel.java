package com.example.dn_mart;



public class AfterShopListModel {



        private String image,name,id,price,discount,quantity,description;

        public AfterShopListModel(String id,String name,String image,String price,String discount,String quantity,String description) {
            this.image = image;
            this.name = name;
            this.id= id;
            this.price=price;
            this.discount=discount;
            this.quantity=quantity;
            this.description=description;

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







