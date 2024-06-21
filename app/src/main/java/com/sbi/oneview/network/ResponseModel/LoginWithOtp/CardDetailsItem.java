package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CardDetailsItem{

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("activityDate")
	private String activityDate;

	@SerializedName("wallBalTransit")
	private String wallBalTransit;

	@SerializedName("lastSyncPersonal")
	private Object lastSyncPersonal;

	@SerializedName("id")
	private int id;

	@SerializedName("cardNumber")
	private String cardNumber;

	@SerializedName("cardStatus")
	private String cardStatus;

	@SerializedName("productName")
	private String productName;

	@SerializedName("expDate")
	private String expDate;

	@SerializedName("wallBalPersonal")
	private String wallBalPersonal;

	@SerializedName("lastSyncTransit")
	private Object lastSyncTransit;

	@SerializedName("cardAccountDetails")
	private List<CardAccountDetailsItem> cardAccountDetails;

	@SerializedName("cardActivDate")
	private String cardActivDate;

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("cardExpiryDate")
	private String cardExpiryDate;

	@SerializedName("sid")
	private Object sid;

	public void setCardRefNumber(String cardRefNumber){
		this.cardRefNumber = cardRefNumber;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setActivityDate(String activityDate){
		this.activityDate = activityDate;
	}

	public String getActivityDate(){
		return activityDate;
	}

	public void setWallBalTransit(String wallBalTransit){
		this.wallBalTransit = wallBalTransit;
	}

	public String getWallBalTransit(){
		return wallBalTransit;
	}

	public void setLastSyncPersonal(Object lastSyncPersonal){
		this.lastSyncPersonal = lastSyncPersonal;
	}

	public Object getLastSyncPersonal(){
		return lastSyncPersonal;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setExpDate(String expDate){
		this.expDate = expDate;
	}

	public String getExpDate(){
		return expDate;
	}

	public void setWallBalPersonal(String wallBalPersonal){
		this.wallBalPersonal = wallBalPersonal;
	}

	public String getWallBalPersonal(){
		return wallBalPersonal;
	}

	public void setLastSyncTransit(Object lastSyncTransit){
		this.lastSyncTransit = lastSyncTransit;
	}

	public Object getLastSyncTransit(){
		return lastSyncTransit;
	}

	public void setCardAccountDetails(List<CardAccountDetailsItem> cardAccountDetails){
		this.cardAccountDetails = cardAccountDetails;
	}

	public List<CardAccountDetailsItem> getCardAccountDetails(){
		return cardAccountDetails;
	}

	public void setCardActivDate(String cardActivDate){
		this.cardActivDate = cardActivDate;
	}

	public String getCardActivDate(){
		return cardActivDate;
	}

	public void setProxyNumber(String proxyNumber){
		this.proxyNumber = proxyNumber;
	}

	public String getProxyNumber(){
		return proxyNumber;
	}

	public void setCardExpiryDate(String cardExpiryDate){
		this.cardExpiryDate = cardExpiryDate;
	}

	public String getCardExpiryDate(){
		return cardExpiryDate;
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
			"CardDetailsItem{" + 
			"cardRefNumber = '" + cardRefNumber + '\'' + 
			",productCode = '" + productCode + '\'' + 
			",activityDate = '" + activityDate + '\'' + 
			",wallBalTransit = '" + wallBalTransit + '\'' + 
			",lastSyncPersonal = '" + lastSyncPersonal + '\'' + 
			",id = '" + id + '\'' + 
			",cardNumber = '" + cardNumber + '\'' + 
			",cardStatus = '" + cardStatus + '\'' + 
			",productName = '" + productName + '\'' + 
			",expDate = '" + expDate + '\'' + 
			",wallBalPersonal = '" + wallBalPersonal + '\'' + 
			",lastSyncTransit = '" + lastSyncTransit + '\'' + 
			",cardAccountDetails = '" + cardAccountDetails + '\'' + 
			",cardActivDate = '" + cardActivDate + '\'' + 
			",proxyNumber = '" + proxyNumber + '\'' + 
			",cardExpiryDate = '" + cardExpiryDate + '\'' + 
			",sid = '" + sid + '\'' + 
			"}";
		}
}