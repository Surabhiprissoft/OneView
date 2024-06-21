package com.sbi.oneview.network.ResponseModel.MiniStatement;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("txnResponses")
	private List<TxnResponsesItem> txnResponses;

	@SerializedName("message")
	private String message;

	@SerializedName("cardStatus")
	private String cardStatus;

	@SerializedName("cardNumber")
	private String cardNumber;

	@SerializedName("txnId")
	private String txnId;

	public void setTxnResponses(List<TxnResponsesItem> txnResponses){
		this.txnResponses = txnResponses;
	}

	public List<TxnResponsesItem> getTxnResponses(){
		return txnResponses;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCardStatus(String cardStatus){
		this.cardStatus = cardStatus;
	}

	public String getCardStatus(){
		return cardStatus;
	}

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	public String getCardNumber(){
		return cardNumber;
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
			"txnResponses = '" + txnResponses + '\'' + 
			",message = '" + message + '\'' + 
			",cardStatus = '" + cardStatus + '\'' + 
			",cardNumber = '" + cardNumber + '\'' + 
			",txnId = '" + txnId + '\'' + 
			"}";
		}
}