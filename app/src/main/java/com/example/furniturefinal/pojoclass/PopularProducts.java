package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class PopularProducts{
	@SerializedName("productId")
	String productId;
	@SerializedName("productName")
	String productName;
	@SerializedName("imageUrl")
	String imageUrl;
	@SerializedName("productRating")
	int productRating;
	@SerializedName("productPrice")
	double productPrice;
//	@SerializedName("name")
//	String merchantName;
//	@SerializedName("merchantId")
//	String merchantId;


	@Override
	public String toString() {
		return "PopularProducts{" +
				"productId='" + productId + '\'' +
				", productName='" + productName + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", productRating=" + productRating +
				", productPrice=" + productPrice +
//				", name='" + merchantName + '\'' +
//				", merchantId='" + merchantId + '\'' +
				'}';
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getProductRating() {
		return productRating;
	}

	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

//	public String getName() {
//		return merchantName;
//	}
//
//	public void setName(String merchantName) {
//		this.merchantName = merchantName;
//	}
//
//	public String getMerchantId() {
//		return merchantId;
//	}
//
//	public void setMerchantId(String merchantId) {
//		this.merchantId = merchantId;
//	}
}
