package com.sbi.oneview.base;

import com.google.gson.annotations.SerializedName;

public class ResponseBaseModel{

	@SerializedName("data")
	private Object data;

	@SerializedName("message")
	private String message;

	@SerializedName("statusCode")
	private int statusCode;

	public void setData(Object data){
		this.data = data;
	}

	public Object getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	@Override
 	public String toString(){
		return 
			"ResponseBaseModel{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			"}";
		}
}