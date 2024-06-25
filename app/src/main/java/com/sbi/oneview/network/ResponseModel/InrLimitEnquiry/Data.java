package com.sbi.oneview.network.ResponseModel.InrLimitEnquiry;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("clTxnAmount")
	private String clTxnAmount;

	@SerializedName("clTxnCount")
	private String clTxnCount;

	@SerializedName("posTxnAmount")
	private String posTxnAmount;

	@SerializedName("atmTxnCount")
	private String atmTxnCount;

	@SerializedName("ecomTxnAmount")
	private String ecomTxnAmount;

	@SerializedName("atmTxnFlag")
	private String atmTxnFlag;

	@SerializedName("atmTxnAmount")
	private String atmTxnAmount;

	@SerializedName("ecomTxnCount")
	private String ecomTxnCount;

	@SerializedName("responseDescr")
	private String responseDescr;

	@SerializedName("responseCode")
	private String responseCode;

	@SerializedName("ecomTxnFlag")
	private String ecomTxnFlag;

	@SerializedName("posTxnCount")
	private String posTxnCount;

	@SerializedName("posTxnFlag")
	private String posTxnFlag;

	@SerializedName("requestCode")
	private String requestCode;

	@SerializedName("clTxnFlag")
	private String clTxnFlag;

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("responseId")
	private String responseId;

	public void setClTxnAmount(String clTxnAmount){
		this.clTxnAmount = clTxnAmount;
	}

	public String getClTxnAmount(){
		return clTxnAmount;
	}

	public void setClTxnCount(String clTxnCount){
		this.clTxnCount = clTxnCount;
	}

	public String getClTxnCount(){
		return clTxnCount;
	}

	public void setPosTxnAmount(String posTxnAmount){
		this.posTxnAmount = posTxnAmount;
	}

	public String getPosTxnAmount(){
		return posTxnAmount;
	}

	public void setAtmTxnCount(String atmTxnCount){
		this.atmTxnCount = atmTxnCount;
	}

	public String getAtmTxnCount(){
		return atmTxnCount;
	}

	public void setEcomTxnAmount(String ecomTxnAmount){
		this.ecomTxnAmount = ecomTxnAmount;
	}

	public String getEcomTxnAmount(){
		return ecomTxnAmount;
	}

	public void setAtmTxnFlag(String atmTxnFlag){
		this.atmTxnFlag = atmTxnFlag;
	}

	public String getAtmTxnFlag(){
		return atmTxnFlag;
	}

	public void setAtmTxnAmount(String atmTxnAmount){
		this.atmTxnAmount = atmTxnAmount;
	}

	public String getAtmTxnAmount(){
		return atmTxnAmount;
	}

	public void setEcomTxnCount(String ecomTxnCount){
		this.ecomTxnCount = ecomTxnCount;
	}

	public String getEcomTxnCount(){
		return ecomTxnCount;
	}

	public void setResponseDescr(String responseDescr){
		this.responseDescr = responseDescr;
	}

	public String getResponseDescr(){
		return responseDescr;
	}

	public void setResponseCode(String responseCode){
		this.responseCode = responseCode;
	}

	public String getResponseCode(){
		return responseCode;
	}

	public void setEcomTxnFlag(String ecomTxnFlag){
		this.ecomTxnFlag = ecomTxnFlag;
	}

	public String getEcomTxnFlag(){
		return ecomTxnFlag;
	}

	public void setPosTxnCount(String posTxnCount){
		this.posTxnCount = posTxnCount;
	}

	public String getPosTxnCount(){
		return posTxnCount;
	}

	public void setPosTxnFlag(String posTxnFlag){
		this.posTxnFlag = posTxnFlag;
	}

	public String getPosTxnFlag(){
		return posTxnFlag;
	}

	public void setRequestCode(String requestCode){
		this.requestCode = requestCode;
	}

	public String getRequestCode(){
		return requestCode;
	}

	public void setClTxnFlag(String clTxnFlag){
		this.clTxnFlag = clTxnFlag;
	}

	public String getClTxnFlag(){
		return clTxnFlag;
	}

	public void setProxyNumber(String proxyNumber){
		this.proxyNumber = proxyNumber;
	}

	public String getProxyNumber(){
		return proxyNumber;
	}

	public void setResponseId(String responseId){
		this.responseId = responseId;
	}

	public String getResponseId(){
		return responseId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"clTxnAmount = '" + clTxnAmount + '\'' + 
			",clTxnCount = '" + clTxnCount + '\'' + 
			",posTxnAmount = '" + posTxnAmount + '\'' + 
			",atmTxnCount = '" + atmTxnCount + '\'' + 
			",ecomTxnAmount = '" + ecomTxnAmount + '\'' + 
			",atmTxnFlag = '" + atmTxnFlag + '\'' + 
			",atmTxnAmount = '" + atmTxnAmount + '\'' + 
			",ecomTxnCount = '" + ecomTxnCount + '\'' + 
			",responseDescr = '" + responseDescr + '\'' + 
			",responseCode = '" + responseCode + '\'' + 
			",ecomTxnFlag = '" + ecomTxnFlag + '\'' + 
			",posTxnCount = '" + posTxnCount + '\'' + 
			",posTxnFlag = '" + posTxnFlag + '\'' + 
			",requestCode = '" + requestCode + '\'' + 
			",clTxnFlag = '" + clTxnFlag + '\'' + 
			",proxyNumber = '" + proxyNumber + '\'' + 
			",responseId = '" + responseId + '\'' + 
			"}";
		}
}