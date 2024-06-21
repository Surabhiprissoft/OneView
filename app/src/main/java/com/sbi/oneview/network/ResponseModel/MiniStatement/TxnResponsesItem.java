package com.sbi.oneview.network.ResponseModel.MiniStatement;

import com.google.gson.annotations.SerializedName;

public class TxnResponsesItem{

	@SerializedName("txnCurrency")
	private String txnCurrency;

	@SerializedName("closingBalance")
	private String closingBalance;

	@SerializedName("txnForm")
	private String txnForm;

	@SerializedName("txnType")
	private String txnType;

	@SerializedName("terminalId")
	private String terminalId;

	@SerializedName("blngCurrency")
	private String blngCurrency;

	@SerializedName("merchantName")
	private String merchantName;

	@SerializedName("mccCode")
	private String mccCode;

	@SerializedName("txnStatus")
	private String txnStatus;

	@SerializedName("txnChannelType")
	private String txnChannelType;

	@SerializedName("merchantId")
	private String merchantId;

	@SerializedName("transactionSettCurrency")
	private String transactionSettCurrency;

	@SerializedName("txnTime")
	private String txnTime;

	@SerializedName("txnFailReason")
	private String txnFailReason;

	@SerializedName("txnDescr")
	private String txnDescr;

	@SerializedName("billingAmount")
	private String billingAmount;

	@SerializedName("accountType")
	private String accountType;

	@SerializedName("txnSettStatus")
	private String txnSettStatus;

	@SerializedName("refernceNo")
	private String refernceNo;

	@SerializedName("accountBalance")
	private String accountBalance;

	@SerializedName("currencyCode")
	private String currencyCode;

	@SerializedName("txnAmount")
	private String txnAmount;

	@SerializedName("txnDate")
	private String txnDate;

	@SerializedName("stlmAmount")
	private String stlmAmount;

	@SerializedName("txnId")
	private String txnId;

	public void setTxnCurrency(String txnCurrency){
		this.txnCurrency = txnCurrency;
	}

	public String getTxnCurrency(){
		return txnCurrency;
	}

	public void setClosingBalance(String closingBalance){
		this.closingBalance = closingBalance;
	}

	public String getClosingBalance(){
		return closingBalance;
	}

	public void setTxnForm(String txnForm){
		this.txnForm = txnForm;
	}

	public String getTxnForm(){
		return txnForm;
	}

	public void setTxnType(String txnType){
		this.txnType = txnType;
	}

	public String getTxnType(){
		return txnType;
	}

	public void setTerminalId(String terminalId){
		this.terminalId = terminalId;
	}

	public String getTerminalId(){
		return terminalId;
	}

	public void setBlngCurrency(String blngCurrency){
		this.blngCurrency = blngCurrency;
	}

	public String getBlngCurrency(){
		return blngCurrency;
	}

	public void setMerchantName(String merchantName){
		this.merchantName = merchantName;
	}

	public String getMerchantName(){
		return merchantName;
	}

	public void setMccCode(String mccCode){
		this.mccCode = mccCode;
	}

	public String getMccCode(){
		return mccCode;
	}

	public void setTxnStatus(String txnStatus){
		this.txnStatus = txnStatus;
	}

	public String getTxnStatus(){
		return txnStatus;
	}

	public void setTxnChannelType(String txnChannelType){
		this.txnChannelType = txnChannelType;
	}

	public String getTxnChannelType(){
		return txnChannelType;
	}

	public void setMerchantId(String merchantId){
		this.merchantId = merchantId;
	}

	public String getMerchantId(){
		return merchantId;
	}

	public void setTransactionSettCurrency(String transactionSettCurrency){
		this.transactionSettCurrency = transactionSettCurrency;
	}

	public String getTransactionSettCurrency(){
		return transactionSettCurrency;
	}

	public void setTxnTime(String txnTime){
		this.txnTime = txnTime;
	}

	public String getTxnTime(){
		return txnTime;
	}

	public void setTxnFailReason(String txnFailReason){
		this.txnFailReason = txnFailReason;
	}

	public String getTxnFailReason(){
		return txnFailReason;
	}

	public void setTxnDescr(String txnDescr){
		this.txnDescr = txnDescr;
	}

	public String getTxnDescr(){
		return txnDescr;
	}

	public void setBillingAmount(String billingAmount){
		this.billingAmount = billingAmount;
	}

	public String getBillingAmount(){
		return billingAmount;
	}

	public void setAccountType(String accountType){
		this.accountType = accountType;
	}

	public String getAccountType(){
		return accountType;
	}

	public void setTxnSettStatus(String txnSettStatus){
		this.txnSettStatus = txnSettStatus;
	}

	public String getTxnSettStatus(){
		return txnSettStatus;
	}

	public void setRefernceNo(String refernceNo){
		this.refernceNo = refernceNo;
	}

	public String getRefernceNo(){
		return refernceNo;
	}

	public void setAccountBalance(String accountBalance){
		this.accountBalance = accountBalance;
	}

	public String getAccountBalance(){
		return accountBalance;
	}

	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public void setTxnAmount(String txnAmount){
		this.txnAmount = txnAmount;
	}

	public String getTxnAmount(){
		return txnAmount;
	}

	public void setTxnDate(String txnDate){
		this.txnDate = txnDate;
	}

	public String getTxnDate(){
		return txnDate;
	}

	public void setStlmAmount(String stlmAmount){
		this.stlmAmount = stlmAmount;
	}

	public String getStlmAmount(){
		return stlmAmount;
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
			"TxnResponsesItem{" + 
			"txnCurrency = '" + txnCurrency + '\'' + 
			",closingBalance = '" + closingBalance + '\'' + 
			",txnForm = '" + txnForm + '\'' + 
			",txnType = '" + txnType + '\'' + 
			",terminalId = '" + terminalId + '\'' + 
			",blngCurrency = '" + blngCurrency + '\'' + 
			",merchantName = '" + merchantName + '\'' + 
			",mccCode = '" + mccCode + '\'' + 
			",txnStatus = '" + txnStatus + '\'' + 
			",txnChannelType = '" + txnChannelType + '\'' + 
			",merchantId = '" + merchantId + '\'' + 
			",transactionSettCurrency = '" + transactionSettCurrency + '\'' + 
			",txnTime = '" + txnTime + '\'' + 
			",txnFailReason = '" + txnFailReason + '\'' + 
			",txnDescr = '" + txnDescr + '\'' + 
			",billingAmount = '" + billingAmount + '\'' + 
			",accountType = '" + accountType + '\'' + 
			",txnSettStatus = '" + txnSettStatus + '\'' + 
			",refernceNo = '" + refernceNo + '\'' + 
			",accountBalance = '" + accountBalance + '\'' + 
			",currencyCode = '" + currencyCode + '\'' + 
			",txnAmount = '" + txnAmount + '\'' + 
			",txnDate = '" + txnDate + '\'' + 
			",stlmAmount = '" + stlmAmount + '\'' + 
			",txnId = '" + txnId + '\'' + 
			"}";
		}
}