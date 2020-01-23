package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class Merchant {
    @SerializedName("merchantName")
    private String merchantName;
    @SerializedName("merchantId")
    private String merchantId;
    @SerializedName("productPrice")
    private int productsPrice;

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantName='" + merchantName + '\'' +
                ", merchantId=" + merchantId +
                ", productPrice=" + productsPrice +
                '}';
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

    public int getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(int productsPrice) {
        this.productsPrice = productsPrice;
    }
}
