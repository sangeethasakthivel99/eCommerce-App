package com.example.furniturefinal.pojoclass;

public class HistoryModel {
    int totalPrice;
    String orderId;
    // String image;
    String date;
    //int quantity;
    public HistoryModel(int totalPrice, String orderId, String date) {
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.date = date;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
