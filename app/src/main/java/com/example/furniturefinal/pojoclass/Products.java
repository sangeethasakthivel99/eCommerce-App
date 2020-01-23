package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Products {
    @SerializedName("productId")
    private int productId;
    @SerializedName("merchantId")
    private int merchantId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("productsPrice")
    private int price;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("attributes")
    private Map<String, String> attributes;
    @SerializedName("productRating")
    private int productRating;
    @SerializedName("otherMerchants")
    private List<Merchant> otherMerchants;

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", merchantId=" + merchantId +
                ", productName='" + productName + '\'' +
                ", productsPrice=" + price +
                ", description='" + description + '\'' +
                ", imageUrl='" + image + '\'' +
                ", attributes=" + attributes +
                ", productRating=" + productRating +
                ", otherMerchants=" + otherMerchants +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public int getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    public List<Merchant> getOtherMerchants() {
        return otherMerchants;
    }

    public void setOtherMerchants(List<Merchant> otherMerchants) {
        this.otherMerchants = otherMerchants;
    }
}
