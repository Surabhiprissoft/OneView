package com.sbi.oneview.network.ResponseModel.TransitStatement;

import com.google.gson.annotations.SerializedName;

public class StatementsItem{

	@SerializedName("date")
	private String date;

	@SerializedName("mechantId")
	private String mechantId;

	@SerializedName("amount")
	private String amount;

	@SerializedName("wallet")
	private String wallet;

	@SerializedName("txnForm")
	private String txnForm;

	@SerializedName("terminalId")
	private String terminalId;

	@SerializedName("type")
	private String type;

	@SerializedName("mcc")
	private String mcc;

	@SerializedName("merchantName")
	private String merchantName;

	@SerializedName("settStatus")
	private String settStatus;

	@SerializedName("currency")
	private String currency;

	@SerializedName("time")
	private String time;

	@SerializedName("txnDesc")
	private String txnDesc;

	@SerializedName("cardNumber")
	private String cardNumber;

	@SerializedName("cardStatus")
	private String cardStatus;

	@SerializedName("txnId")
	private String txnId;

	@SerializedName("status")
	private String status;

	@SerializedName("failedReason")
	private String failedReason;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setMechantId(String mechantId){
		this.mechantId = mechantId;
	}

	public String getMechantId(){
		return mechantId;
	}

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return amount;
	}

	public void setWallet(String wallet){
		this.wallet = wallet;
	}

	public String getWallet(){
		return wallet;
	}

	public void setTxnForm(String txnForm){
		this.txnForm = txnForm;
	}

	public String getTxnForm(){
		return txnForm;
	}

	public void setTerminalId(String terminalId){
		this.terminalId = terminalId;
	}

	public String getTerminalId(){
		return terminalId;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setMcc(String mcc){
		this.mcc = mcc;
	}

	public String getMcc(){
		return mcc;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}

	public void setSettStatus(String settStatus){
		this.settStatus = settStatus;
	}

	public String getSettStatus(){
		return settStatus;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setTxnDesc(String txnDesc){
		this.txnDesc = txnDesc;
	}

	public String getTxnDesc(){
		return txnDesc;
	}

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public void setCardStatus(String cardStatus){
		this.cardStatus = cardStatus;
	}

	public String getCardStatus(){
		return cardStatus;
	}

	public void setTxnId(String txnId){
		this.txnId = txnId;
	}

	public String getTxnId(){
		return txnId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setFailedReason(String failedReason){
		this.failedReason = failedReason;
	}

	public String getFailedReason(){
		return failedReason;
	}

	@Override
 	public String toString(){
		return 
			"StatementsItem{" + 
			"date = '" + date + '\'' + 
			",mechantId = '" + mechantId + '\'' + 
			",amount = '" + amount + '\'' + 
			",wallet = '" + wallet + '\'' + 
			",txnForm = '" + txnForm + '\'' + 
			",terminalId = '" + terminalId + '\'' + 
			",type = '" + type + '\'' + 
			",mcc = '" + mcc + '\'' + 
			",merchantName = '" + merchantName + '\'' + 
			",settStatus = '" + settStatus + '\'' + 
			",currency = '" + currency + '\'' + 
			",time = '" + time + '\'' + 
			",txnDesc = '" + txnDesc + '\'' + 
			",cardNumber = '" + cardNumber + '\'' + 
			",cardStatus = '" + cardStatus + '\'' + 
			",txnId = '" + txnId + '\'' + 
			",status = '" + status + '\'' + 
			",failedReason = '" + failedReason + '\'' + 
			"}";
		}
}