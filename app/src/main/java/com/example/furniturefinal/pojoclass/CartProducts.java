package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class CartProducts {
    @SerializedName("order_id")
    String order_id;
    @SerializedName("product_name")
    String product_name;
    @SerializedName("price")
    String price;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartProducts{" +
                "order_id='" + order_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
