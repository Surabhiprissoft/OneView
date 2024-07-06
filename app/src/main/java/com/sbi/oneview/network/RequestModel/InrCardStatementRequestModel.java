package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class InrCardStatementRequestModel{

	@SerializedName("fromDate")
	private String fromDate;

	@SerializedName("toDate")
	private String toDate;

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("type")
	private String type;

	@SerializedName("sId")
	private String sId;

	public void setFromDate(String fromDate){
		this.fromDate = fromDate;
	}

	public String getFromDate(){
		return fromDate;
	}

	public void setToDate(String toDate){
		this.toDate = toDate;
	}

	public String getToDate(){
		return toDate;
	}

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
			"InrCardStatementRequestModel{" + 
			"fromDate = '" + fromDate + '\'' + 
			",toDate = '" + toDate + '\'' + 
			",proxyNumber = '" + proxyNumber + '\'' + 
			",type = '" + type + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}