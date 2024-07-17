package com.sbi.oneview.network.RequestModel.Transit;

import com.google.gson.annotations.SerializedName;

public class TransitProcessHotlistRequestModel {

	@SerializedName("reason")
	private String reason;

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("refTxnId")
	private String refTxnId;

	@SerializedName("otp")
	private String otp;

	@SerializedName("sId")
	private String sId;

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setCardRefNumber(String cardRefNumber){
		this.cardRefNumber = cardRefNumber;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public void setRefTxnId(String refTxnId){
		this.refTxnId = refTxnId;
	}

	public String getRefTxnId(){
		return refTxnId;
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
			"TransitProcessHotlistResponseModel{" + 
			"reason = '" + reason + '\'' + 
			",cardRefNumber = '" + cardRefNumber + '\'' + 
			",refTxnId = '" + refTxnId + '\'' + 
			",otp = '" + otp + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}