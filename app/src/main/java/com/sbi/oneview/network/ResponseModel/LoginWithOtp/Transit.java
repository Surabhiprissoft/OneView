package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Transit{

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("ovdValue")
	private String ovdValue;

	@SerializedName("kyc")
	private String kyc;

	@SerializedName("dob")
	private String dob;

	@SerializedName("cardDetails")
	private List<CardDetailsItem> cardDetails;

	@SerializedName("middleName")
	private String middleName;

	@SerializedName("id")
	private int id;

	@SerializedName("userName")
	private String userName;

	@SerializedName("email")
	private Object email;

	@SerializedName("ovdType")
	private String ovdType;

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

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setEmail(Object email){
		this.email = email;
	}

	public Object getEmail(){
		return email;
	}

	public void setOvdType(String ovdType){
		this.ovdType = ovdType;
	}

	public String getOvdType(){
		return ovdType;
	}

	@Override
 	public String toString(){
		return 
			"Transit{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",ovdValue = '" + ovdValue + '\'' + 
			",kyc = '" + kyc + '\'' + 
			",dob = '" + dob + '\'' + 
			",cardDetails = '" + cardDetails + '\'' + 
			",middleName = '" + middleName + '\'' + 
			",id = '" + id + '\'' + 
			",userName = '" + userName + '\'' + 
			",email = '" + email + '\'' + 
			",ovdType = '" + ovdType + '\'' + 
			"}";
		}
}