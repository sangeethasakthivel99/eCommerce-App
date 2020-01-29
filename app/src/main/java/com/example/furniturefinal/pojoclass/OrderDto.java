package com.example.furniturefinal.pojoclass;

import com.example.furniturefinal.database.CartProduct;

import java.util.List;

public class OrderDto {
    private String customerId;
    private String address;
    private List<CartProduct> orderedItemDto;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CartProduct> getOrderedItemDto() {
        return orderedItemDto;
    }

    public void setOrderedItemDto(List<CartProduct> orderedItemDto) {
        this.orderedItemDto = orderedItemDto;
    }
}
