package com.sbi.oneview.network.RequestModel.Transit;

import com.google.gson.annotations.SerializedName;

public class ResendOtpRequestModel{

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("mobileNumber")
	private String mobileNumber;

	@SerializedName("action")
	private String action;

	@SerializedName("sId")
	private String sId;

	public void setCardRefNumber(String cardRefNumber){
		this.cardRefNumber = cardRefNumber;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return action;
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
			"ResendOtpRequestModel{" + 
			"cardRefNumber = '" + cardRefNumber + '\'' + 
			",mobileNumber = '" + mobileNumber + '\'' + 
			",action = '" + action + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}