package com.example.furniturefinal.pojoclass;

public class UserModel {
    String imageUrl;
    String name,address,contactNo,email;

    public UserModel(String imageUrl, String name, String address, String contactNo, String email) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
