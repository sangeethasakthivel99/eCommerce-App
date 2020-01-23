package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class Categories{

	@SerializedName("categoryId")
	private String categoryId;
	@SerializedName("categoryName")
	private String categoryName;
	@SerializedName("categoryImageUrl")
	private String categoryImageUrl;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryImageUrl() {
		return categoryImageUrl;
	}

	public void setCategoryImageUrl(String categoryImageUrl) {
		this.categoryImageUrl = categoryImageUrl;
	}

	@Override
	public String toString() {
		return "Categories{" +
				"categoryId='" + categoryId + '\'' +
				", categoryName='" + categoryName + '\'' +
				", categoryImageUrl='" + categoryImageUrl + '\'' +
				'}';
	}
}
