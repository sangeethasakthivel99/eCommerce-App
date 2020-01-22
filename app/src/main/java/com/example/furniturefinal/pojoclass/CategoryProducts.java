package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class CategoryProducts {
    @SerializedName("product_id")
    String product_id;
    @SerializedName("product_name")
    String product_name;
    @SerializedName("image")
	String image;
    @SerializedName("ratings")
	int ratings;
    @SerializedName("price")
    int price;
    @SerializedName("merchantName")
    String merchantName;
    @SerializedName("merchantId")
    String merchantId;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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
                "product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", image='" + image + '\'' +
                ", ratings=" + ratings +
                ", price=" + price +
                ", merchantName='" + merchantName + '\'' +
                ", merchantId='" + merchantId + '\'' +
                '}';
    }
}
