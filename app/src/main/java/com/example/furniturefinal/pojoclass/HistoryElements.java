package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
public class HistoryElements {
    @SerializedName("orderId")
    String orderId;
    @SerializedName("date")
    Date date;
    @SerializedName("totalPrice")
    String totalPrice;
    @SerializedName("customerId")
    String customerId;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    @Override
    public String toString() {
        return "HistoryElements{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", totalPrice='" + totalPrice + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
