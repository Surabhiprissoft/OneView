package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class CardMiniStatementRequestModel{

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("type")
	private String type;

	@SerializedName("sId")
	private String sId;

	public void setProxyNumber(String proxyNumber){
		this.proxyNumber = proxyNumber;
	}

	public String getProxyNumber(){
		return proxyNumber;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
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
			"CardMiniStatementRequestModel{" + 
			"proxyNumber = '" + proxyNumber + '\'' + 
			",type = '" + type + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}