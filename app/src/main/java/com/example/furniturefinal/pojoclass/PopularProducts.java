package com.example.furniturefinal.pojoclass;

import com.google.gson.annotations.SerializedName;

public class PopularProducts{
	@SerializedName("msg")
	private String msg;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	@Override
 	public String toString(){
		return 
			"PopularProducts{" + 
			"msg = '" + msg + '\'' + 
			"}";
		}
}
