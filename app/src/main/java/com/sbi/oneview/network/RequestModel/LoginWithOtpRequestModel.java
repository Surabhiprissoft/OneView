package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class LoginWithOtpRequestModel{

	@SerializedName("otp")
	private String otp;

	@SerializedName("username")
	private String username;

	@SerializedName("sId")
	private String sId;

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

	public void setSId(String sId){
		this.sId = sId;
	}

	public String getSId(){
		return sId;
	}

	@Override
 	public String toString(){
		return 
			"LoginWithOtpRequestModel{" + 
			"otp = '" + otp + '\'' + 
			",username = '" + username + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}