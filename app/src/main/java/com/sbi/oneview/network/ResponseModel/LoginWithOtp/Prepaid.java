package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Prepaid{

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("ovdValue")
	private String ovdValue;

	@SerializedName("kyc")
	private String kyc;

	@SerializedName("mobileNumber")
	private String mobileNumber;

	@SerializedName("dob")
	private String dob;

	@SerializedName("cardDetails")
	public List<CardDetailsItem> cardDetails;

	@SerializedName("middleName")
	private String middleName;

	@SerializedName("id")
	private int id;

	@SerializedName("ovdType")
	private String ovdType;

	@SerializedName("sid")
	private Object sid;

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setOvdValue(String ovdValue){
		this.ovdValue = ovdValue;
	}

	public String getOvdValue(){
		return ovdValue;
	}

	public void setKyc(String kyc){
		this.kyc = kyc;
	}

	public String getKyc(){
		return kyc;
	}

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setCardDetails(List<CardDetailsItem> cardDetails){
		this.cardDetails = cardDetails;
	}

	public List<CardDetailsItem> getCardDetails(){
		return cardDetails;
	}

	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	public String getMiddleName(){
		return middleName;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOvdType(String ovdType){
		this.ovdType = ovdType;
	}

	public String getOvdType(){
		return ovdType;
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
			"Prepaid{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",ovdValue = '" + ovdValue + '\'' + 
			",kyc = '" + kyc + '\'' + 
			",mobileNumber = '" + mobileNumber + '\'' + 
			",dob = '" + dob + '\'' + 
			",cardDetails = '" + cardDetails + '\'' + 
			",middleName = '" + middleName + '\'' + 
			",id = '" + id + '\'' + 
			",ovdType = '" + ovdType + '\'' + 
			",sid = '" + sid + '\'' + 
			"}";
		}
}