package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class CardBlockUnblockRequestModel{

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("sId")
	private String sId;

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
			"CardBlockUnblockRequestModel{" + 
			"proxyNumber = '" + proxyNumber + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}