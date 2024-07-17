package com.sbi.oneview.network.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class InrResetPinResponseModel{

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
			"InrResetPinResponseModel{" + 
			"message = '" + message + '\'' + 
			",targetUrl = '" + targetUrl + '\'' + 
			"}";
		}
}