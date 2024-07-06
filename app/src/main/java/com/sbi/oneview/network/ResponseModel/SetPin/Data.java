package com.sbi.oneview.network.ResponseModel.SetPin;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("message")
	private String message;

	@SerializedName("targetUrl")
	private String targetUrl;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTargetUrl(String targetUrl){
		this.targetUrl = targetUrl;
	}

	public String getTargetUrl(){
		return targetUrl;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"message = '" + message + '\'' + 
			",targetUrl = '" + targetUrl + '\'' + 
			"}";
		}
}