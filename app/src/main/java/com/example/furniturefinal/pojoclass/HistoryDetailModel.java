package com.example.furniturefinal.pojoclass;

public class HistoryDetailModel {
    private String productName;
    private String imageUrl;
    private int quantityBrought;
    private double productPrice;
    public HistoryDetailModel(String productName, String imageUrl, int quantityBrought, double productPrice) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.quantityBrought = quantityBrought;
        this.productPrice = productPrice;
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
    public int getQuantityBrought() {
        return quantityBrought;
    }
    public void setQuantityBrought(int quantityBrought) {
        this.quantityBrought = quantityBrought;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}