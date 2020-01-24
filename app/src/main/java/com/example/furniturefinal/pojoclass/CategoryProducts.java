package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class CategoryProducts {
    @SerializedName("productId")
    String productId;
    @SerializedName("productName")
    String productName;
    @SerializedName("imageUrl")
	String imageUrl;
    @SerializedName("productRating")
	int productRating;
    @SerializedName("productPrice")
    int productPrice;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "CategoryProducts{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productRating=" + productRating +
                ", productPrice=" + productPrice +
                '}';
    }
}
