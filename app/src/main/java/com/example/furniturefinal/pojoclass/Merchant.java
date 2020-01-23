package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class Merchant {
    @SerializedName("name")
    private String name;
    @SerializedName("merchantId")
    private int merchantId;
    @SerializedName("productsPrice")
    private int productsPrice;

    @Override
    public String toString() {
        return "Merchant{" +
                "name='" + name + '\'' +
                ", merchantId=" + merchantId +
                ", productsPrice=" + productsPrice +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public int getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(int productsPrice) {
        this.productsPrice = productsPrice;
    }
}
