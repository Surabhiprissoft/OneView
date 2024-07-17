package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class LimitUpdateRequestModel{

	@SerializedName("clTxnAmount")
	private String clTxnAmount;

	@SerializedName("posTxnAmount")
	private String posTxnAmount;

	@SerializedName("clTxnCount")
	private String clTxnCount;

	@SerializedName("atmTxnCount")
	private String atmTxnCount;

	@SerializedName("ecomTxnAmount")
	private String ecomTxnAmount;

	@SerializedName("atmTxnFlag")
	private String atmTxnFlag;

	@SerializedName("ecomTxnCount")
	private String ecomTxnCount;

	@SerializedName("atmTxnAmount")
	private String atmTxnAmount;

	@SerializedName("ecomTxnFlag")
	private String ecomTxnFlag;

	@SerializedName("posTxnCount")
	private String posTxnCount;

	@SerializedName("posTxnFlag")
	private String posTxnFlag;

	@SerializedName("clTxnFlag")
	private String clTxnFlag;

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("remarks")
	private String remarks;

	public void setClTxnAmount(String clTxnAmount){
		this.clTxnAmount = clTxnAmount;
	}

	public String getClTxnAmount(){
		return clTxnAmount;
	}

	public void setPosTxnAmount(String posTxnAmount){
		this.posTxnAmount = posTxnAmount;
	}

	public String getPosTxnAmount(){
		return posTxnAmount;
	}

	public void setClTxnCount(String clTxnCount){
		this.clTxnCount = clTxnCount;
	}

	public String getClTxnCount(){
		return clTxnCount;
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

	public void setEcomTxnCount(String ecomTxnCount){
		this.ecomTxnCount = ecomTxnCount;
	}

	public String getEcomTxnCount(){
		return ecomTxnCount;
	}

	public void setAtmTxnAmount(String atmTxnAmount){
		this.atmTxnAmount = atmTxnAmount;
	}

	public String getAtmTxnAmount(){
		return atmTxnAmount;
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

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	public String getRemarks(){
		return remarks;
	}

	@Override
 	public String toString(){
		return 
			"LimitUpdateRequestModel{" + 
			"clTxnAmount = '" + clTxnAmount + '\'' + 
			",posTxnAmount = '" + posTxnAmount + '\'' + 
			",clTxnCount = '" + clTxnCount + '\'' + 
			",atmTxnCount = '" + atmTxnCount + '\'' + 
			",ecomTxnAmount = '" + ecomTxnAmount + '\'' + 
			",atmTxnFlag = '" + atmTxnFlag + '\'' + 
			",ecomTxnCount = '" + ecomTxnCount + '\'' + 
			",atmTxnAmount = '" + atmTxnAmount + '\'' + 
			",ecomTxnFlag = '" + ecomTxnFlag + '\'' + 
			",posTxnCount = '" + posTxnCount + '\'' + 
			",posTxnFlag = '" + posTxnFlag + '\'' + 
			",clTxnFlag = '" + clTxnFlag + '\'' + 
			",proxyNumber = '" + proxyNumber + '\'' + 
			",remarks = '" + remarks + '\'' + 
			"}";
		}
}