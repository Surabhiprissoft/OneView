package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class TransitMiniStatementRequestModel{

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("sId")
	private String sId;

	public void setCardRefNumber(String cardRefNumber){
		this.cardRefNumber = cardRefNumber;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
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
			"TransitMiniStatementRequestModel{" + 
			"cardRefNumber = '" + cardRefNumber + '\'' + 
			",productCode = '" + productCode + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}