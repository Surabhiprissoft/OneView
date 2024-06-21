package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import com.google.gson.annotations.SerializedName;

public class CardAccountDetailsItem{

	@SerializedName("accountStatus")
	private String accountStatus;

	@SerializedName("accountType")
	private String accountType;

	@SerializedName("accountNo")
	private String accountNo;

	@SerializedName("id")
	private int id;

	@SerializedName("accountBalance")
	private String accountBalance;

	@SerializedName("currencyCode")
	private String currencyCode;

	@SerializedName("sid")
	private Object sid;

	public void setAccountStatus(String accountStatus){
		this.accountStatus = accountStatus;
	}

	public String getAccountStatus(){
		return accountStatus;
	}

	public void setAccountType(String accountType){
		this.accountType = accountType;
	}

	public String getAccountType(){
		return accountType;
	}

	public void setAccountNo(String accountNo){
		this.accountNo = accountNo;
	}

	public String getAccountNo(){
		return accountNo;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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

	public void setSid(Object sid){
		this.sid = sid;
	}

	public Object getSid(){
		return sid;
	}

	@Override
 	public String toString(){
		return 
			"CardAccountDetailsItem{" + 
			"accountStatus = '" + accountStatus + '\'' + 
			",accountType = '" + accountType + '\'' + 
			",accountNo = '" + accountNo + '\'' + 
			",id = '" + id + '\'' + 
			",accountBalance = '" + accountBalance + '\'' + 
			",currencyCode = '" + currencyCode + '\'' + 
			",sid = '" + sid + '\'' + 
			"}";
		}
}