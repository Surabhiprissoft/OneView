package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Ftc{

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
	private List<CardDetailsItem> cardDetails;

	@SerializedName("middleName")
	private String middleName;

	@SerializedName("id")
	private int id;

	@SerializedName("ovdType")
	private String ovdType;

	@SerializedName("sid")
	private String sid;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getOvdValue(){
		return ovdValue;
	}

	public String getKyc(){
		return kyc;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	public String getDob(){
		return dob;
	}

	public List<CardDetailsItem> getCardDetails(){
		return cardDetails;
	}

	public String getMiddleName(){
		return middleName;
	}

	public int getId(){
		return id;
	}

	public String getOvdType(){
		return ovdType;
	}

	public String getSid(){
		return sid;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setOvdValue(String ovdValue) {
		this.ovdValue = ovdValue;
	}

	public void setKyc(String kyc) {
		this.kyc = kyc;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setCardDetails(List<CardDetailsItem> cardDetails) {
		this.cardDetails = cardDetails;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOvdType(String ovdType) {
		this.ovdType = ovdType;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}