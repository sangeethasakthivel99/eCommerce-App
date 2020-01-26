package com.example.furniturefinal.pojoclass;

import java.util.List;

public class DummyCartDto {
    private String customerId;
    private List<DummyCartListDto> cartDtoList;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<DummyCartListDto> getCartDtoList() {
        return cartDtoList;
    }

    public void setCartDtoList(List<DummyCartListDto> cartDtoList) {
        this.cartDtoList = cartDtoList;
    }
}
