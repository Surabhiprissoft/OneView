package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import com.google.gson.annotations.SerializedName;

public class LoginWithOtpResponseModel{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("statusCode")
	private int statusCode;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public int getStatusCode(){
		return statusCode;
	}
}