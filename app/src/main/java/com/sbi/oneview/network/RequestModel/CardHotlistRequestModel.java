package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class CardHotlistRequestModel{

	@SerializedName("action")
	private String action;

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("sId")
	private String sId;

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return action;
	}

	public void setProxyNumber(String proxyNumber){
		this.proxyNumber = proxyNumber;
	}

	public String getProxyNumber(){
		return proxyNumber;
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
			"CardHotlistRequestModel{" + 
			"action = '" + action + '\'' + 
			",proxyNumber = '" + proxyNumber + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}