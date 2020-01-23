package com.example.furniturefinal.pojoclass;

import java.util.List;

public class ProductDetailResponse {

    private Products product;
    private List<Merchant> merchantList;

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public List<Merchant> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<Merchant> merchantList) {
        this.merchantList = merchantList;
    }
}
