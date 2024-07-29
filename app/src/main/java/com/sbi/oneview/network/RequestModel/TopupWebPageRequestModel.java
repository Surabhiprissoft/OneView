package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class TopupWebPageRequestModel{


	@SerializedName("merchIdVal")
	private String merchIdVal;

	@SerializedName("encData")
	private String encData;



	public void setMerchIdVal(String merchIdVal){
		this.merchIdVal = merchIdVal;
	}

	public String getMerchIdVal(){
		return merchIdVal;
	}

	public void setEncData(String encData){
		this.encData = encData;
	}

	public String getEncData(){
		return encData;
	}

	@Override
 	public String toString(){
		return 
			"TopupWebPageRequestModel{" +
			",merchIdVal = '" + merchIdVal + '\'' + 
			",encData = '" + encData + '\'' + 
			"}";
		}
}