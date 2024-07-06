package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class SetPinRequestModel{

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("sid")
	private String sid;

	public void setProxyNumber(String proxyNumber){
		this.proxyNumber = proxyNumber;
	}

	public String getProxyNumber(){
		return proxyNumber;
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
			"SetPinRequestModel{" + 
			"proxyNumber = '" + proxyNumber + '\'' + 
			",sid = '" + sid + '\'' + 
			"}";
		}
}