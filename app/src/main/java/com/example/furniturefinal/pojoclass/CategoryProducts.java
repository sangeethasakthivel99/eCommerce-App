package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class CategoryProducts {
    @SerializedName("productId")
    String productId;
    @SerializedName("productName")
    String productName;
    @SerializedName("image")
	String image;
    @SerializedName("productRatings")
	int ratings;
    @SerializedName("productsPrice")
    int price;
    @SerializedName("name")
    String name;
    @SerializedName("merchantId")
    String merchantId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "CategoryProducts{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", imageUrl='" + image + '\'' +
                ", productRatings=" + ratings +
                ", productsPrice=" + price +
                ", name='" + name + '\'' +
                ", merchantId='" + merchantId + '\'' +
                '}';
    }
}
