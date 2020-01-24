package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Products {
    @SerializedName("productId")
    private int productId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("productPrice")
    private int productPrice;
    @SerializedName("productDescription")
    private String description;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("productAttributes")
    private Map<String, String> productAttributes;
    @SerializedName("productRating")
    private int productRating;
    @SerializedName("merchantList")
    private List<Merchant> merchantList;

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productAttributes=" + productAttributes +
                ", productRating=" + productRating +
                ", merchantList=" + merchantList +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Map<String, String> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(Map<String, String> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    public List<Merchant> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<Merchant> merchantList) {
        this.merchantList = merchantList;
    }
}
