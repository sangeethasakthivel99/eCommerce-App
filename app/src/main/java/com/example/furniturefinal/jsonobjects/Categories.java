package com.example.furniturefinal.jsonobjects;

import com.google.gson.annotations.SerializedName;

public class Categories{

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
			"Categories{" + 
			"msg = '" + msg + '\'' + 
			"}";
		}
}
