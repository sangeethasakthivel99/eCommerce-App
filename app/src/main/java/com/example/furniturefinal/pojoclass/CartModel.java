package com.example.furniturefinal.pojoclass;

public class CartModel {
    int image;
    String productName,productPrice;

    public CartModel(int image, String productName, String productPrice) {
        this.image = image;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
