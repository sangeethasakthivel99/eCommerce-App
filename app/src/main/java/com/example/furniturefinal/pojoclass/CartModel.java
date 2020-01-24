package com.example.furniturefinal.pojoclass;

public class CartModel {
    String image;
    String productName, productPrice;
    int quantity;

    public CartModel(String image, String productName, String productPrice, int count) {
        this.image = image;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
