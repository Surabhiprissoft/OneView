package com.sbi.oneview.network.RequestModel.Transit;

import com.google.gson.annotations.SerializedName;

public class TransitInitiateTopupRequestModel{

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("amount")
	private int amount;

	@SerializedName("sId")
	private String sId;

	public void setCardRefNumber(String cardRefNumber){
		this.cardRefNumber = cardRefNumber;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
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
			"TransitInitiateTopupRequestModel{" + 
			"cardRefNumber = '" + cardRefNumber + '\'' + 
			",amount = '" + amount + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}