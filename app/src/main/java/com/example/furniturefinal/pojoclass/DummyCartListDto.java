package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class DummyCartListDto {
    @SerializedName("productName")
    String productName;
    @SerializedName("productId")
    String productId;
    @SerializedName("name")
    String name;
    @SerializedName("merchantId")
    String merchantId;
    @SerializedName("image")
    String image;
    @SerializedName("productPrice")
    int price;
    @SerializedName("quantityBrought")
    int quantityBrought;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
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

    public int getQuantityBrought() {
        return quantityBrought;
    }

    public void setQuantityBrought(int quantityBrought) {
        this.quantityBrought = quantityBrought;
    }

    @Override
    public String toString() {
        return "DummyCartListDto{" +
                "productName='" + productName + '\'' +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", merchantId=" + merchantId +
                ", imageUrl='" + image + '\'' +
                ", productPrice=" + price +
                ", quantityBrought=" + quantityBrought +
                '}';
    }
}
