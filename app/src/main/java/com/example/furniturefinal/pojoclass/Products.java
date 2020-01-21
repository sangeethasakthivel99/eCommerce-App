package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class Products {
    @SerializedName("productId")
    private int productId;
    @SerializedName("merchantId")
    private int merchantId;

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", merchantId=" + merchantId +
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
}
