package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class LogoutRequestModel{

	@SerializedName("otp")
	private String otp;

	@SerializedName("username")
	private String username;

	@SerializedName("sid")
	private String sid;

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setSid(String sid){
		this.sid = sid;
	}

	public String getSid(){
		return sid;
	}

	@Override
 	public String toString(){
		return 
			"LogoutRequestModel{" + 
			"otp = '" + otp + '\'' + 
			",username = '" + username + '\'' + 
			",sid = '" + sid + '\'' + 
			"}";
		}
}