package com.sbi.oneview.network.RequestModel.Transit;

import com.google.gson.annotations.SerializedName;

public class ProcessEformRequestModel{

	@SerializedName("refTxnId")
	private String refTxnId;

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("otp")
	private String otp;

	@SerializedName("sId")
	private String sId;

	public void setRefTxnId(String refTxnId){
		this.refTxnId = refTxnId;
	}

	public String getRefTxnId(){
		return refTxnId;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setOtp(String otp){
		this.otp = otp;
	}

	public String getOtp(){
		return otp;
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
			"ProcessEformRequestModel{" + 
			"refTxnId = '" + refTxnId + '\'' + 
			",productCode = '" + productCode + '\'' + 
			",otp = '" + otp + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}