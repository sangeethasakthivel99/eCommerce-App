package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class Merchant {
    @SerializedName("merchantName")
    private String merchantName;
    @SerializedName("merchantId")
    private int merchantId;
    @SerializedName("price")
    private int price;

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantName='" + merchantName + '\'' +
                ", merchantId=" + merchantId +
                ", price=" + price +
                '}';
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
