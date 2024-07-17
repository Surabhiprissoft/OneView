package com.sbi.oneview.network.ResponseModel.TransitRequestHotlist;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("description")
	private String description;

	@SerializedName("message")
	private String message;

	@SerializedName("txnId")
	private String txnId;

	public void setCardRefNumber(String cardRefNumber){
		this.cardRefNumber = cardRefNumber;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTxnId(String txnId){
		this.txnId = txnId;
	}

	public String getTxnId(){
		return txnId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"cardRefNumber = '" + cardRefNumber + '\'' + 
			",description = '" + description + '\'' + 
			",message = '" + message + '\'' + 
			",txnId = '" + txnId + '\'' + 
			"}";
		}
}