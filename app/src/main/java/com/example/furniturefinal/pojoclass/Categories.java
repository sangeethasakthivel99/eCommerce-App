package com.example.furniturefinal.pojoclass;

public class Categories {
	private String categoryImageUrl;
	private String categoryName;
	private String categoryId;

	public void setCategoryImageUrl(String categoryImageUrl){
		this.categoryImageUrl = categoryImageUrl;
	}

	public String getCategoryImageUrl(){
		return categoryImageUrl;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return 
			"Categories{" +
			"categoryImageUrl = '" + categoryImageUrl + '\'' + 
			",categoryName = '" + categoryName + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}
