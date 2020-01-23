package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class PopularProducts{
	@SerializedName("productId")
	String productId;
	@SerializedName("productName")
	String productName;
	@SerializedName("imageUrl")
	String imageUrl;
	@SerializedName("productRatings")
	int productRatings;
	@SerializedName("productPrice")
	int productsPrice;
	@SerializedName("name")
	String merchantName;
	@SerializedName("merchantId")
	String merchantId;


	@Override
	public String toString() {
		return "PopularProducts{" +
				"productId='" + productId + '\'' +
				", productName='" + productName + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", productRatings=" + productRatings +
				", productsPrice=" + productsPrice +
				", name='" + merchantName + '\'' +
				", merchantId='" + merchantId + '\'' +
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

	public int getProductRatings() {
		return productRatings;
	}

	public void setProductRatings(int productRatings) {
		this.productRatings = productRatings;
	}

	public int getProductsPrice() {
		return productsPrice;
	}

	public void setProductsPrice(int productsPrice) {
		this.productsPrice = productsPrice;
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
}
