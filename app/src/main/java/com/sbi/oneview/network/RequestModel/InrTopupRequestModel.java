package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class InrTopupRequestModel{

	@SerializedName("remark")
	private String remark;

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("topUpAmount")
	private String topUpAmount;

	@SerializedName("sId")
	private String sId;

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setProxyNumber(String proxyNumber){
		this.proxyNumber = proxyNumber;
	}

	public String getProxyNumber(){
		return proxyNumber;
	}

	public void setTopUpAmount(String topUpAmount){
		this.topUpAmount = topUpAmount;
	}

	public String getTopUpAmount(){
		return topUpAmount;
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
			"InrTopupRequestModel{" + 
			"remark = '" + remark + '\'' + 
			",proxyNumber = '" + proxyNumber + '\'' + 
			",topUpAmount = '" + topUpAmount + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}