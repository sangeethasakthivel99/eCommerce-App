package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class CartProducts {
    @SerializedName("productName")
    String productName;
    @SerializedName("productId")
    int productId;
    @SerializedName("name")
    String name;
    @SerializedName("merchantId")
    int merchantId;
    @SerializedName("image")
    String image;
    @SerializedName("productsPrice")
    int price;
    @SerializedName("quantity")
    int quantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartProducts{" +
                "productName='" + productName + '\'' +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", merchantId=" + merchantId +
                ", imageUrl='" + image + '\'' +
                ", productsPrice=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
