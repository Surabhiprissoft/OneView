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
	private String sid;

	public String getAccountStatus(){
		return accountStatus;
	}

	public String getAccountType(){
		return accountType;
	}

	public String getAccountNo(){
		return accountNo;
	}

	public int getId(){
		return id;
	}

	public String getAccountBalance(){
		return accountBalance;
	}

	public String getCurrencyCode(){
		return currencyCode;
	}

	public String getSid(){
		return sid;
	}
}