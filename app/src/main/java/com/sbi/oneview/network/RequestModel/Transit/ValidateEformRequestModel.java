package com.sbi.oneview.network.RequestModel.Transit;

import com.google.gson.annotations.SerializedName;

public class ValidateEformRequestModel{

	@SerializedName("firstName")
	private String firstName;

	@SerializedName("lastName")
	private String lastName;

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("ovdValue")
	private String ovdValue;

	@SerializedName("dob")
	private String dob;

	@SerializedName("middleName")
	private String middleName;

	@SerializedName("salutation")
	private String salutation;

	@SerializedName("mobileNo")
	private String mobileNo;

	@SerializedName("pan")
	private String pan;

	@SerializedName("ovdType")
	private String ovdType;

	@SerializedName("sId")
	private String sId;

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

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setOvdValue(String ovdValue){
		this.ovdValue = ovdValue;
	}

	public String getOvdValue(){
		return ovdValue;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}

	public String getMiddleName(){
		return middleName;
	}

	public void setSalutation(String salutation){
		this.salutation = salutation;
	}

	public String getSalutation(){
		return salutation;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public void setPan(String pan){
		this.pan = pan;
	}

	public String getPan(){
		return pan;
	}

	public void setOvdType(String ovdType){
		this.ovdType = ovdType;
	}

	public String getOvdType(){
		return ovdType;
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
			"ValidateEformRequestModel{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",productCode = '" + productCode + '\'' + 
			",ovdValue = '" + ovdValue + '\'' + 
			",dob = '" + dob + '\'' + 
			",middleName = '" + middleName + '\'' + 
			",salutation = '" + salutation + '\'' + 
			",mobileNo = '" + mobileNo + '\'' + 
			",pan = '" + pan + '\'' + 
			",ovdType = '" + ovdType + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}