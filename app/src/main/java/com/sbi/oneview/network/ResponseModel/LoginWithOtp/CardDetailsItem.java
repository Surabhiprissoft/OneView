package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CardDetailsItem{

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("cardAccountDetails")
	private List<CardAccountDetailsItem> cardAccountDetails;

	@SerializedName("id")
	private int id;

	@SerializedName("cardActivDate")
	private String cardActivDate;

	@SerializedName("proxyNumber")
	private String proxyNumber;

	@SerializedName("cardExpiryDate")
	private String cardExpiryDate;

	@SerializedName("productName")
	private String productName;

	@SerializedName("cardStatus")
	private String cardStatus;

	@SerializedName("cardNumber")
	private String cardNumber;

	@SerializedName("sid")
	private String sid;

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("activityDate")
	private String activityDate;

	@SerializedName("wallBalTransit")
	private String wallBalTransit;

	@SerializedName("lastSyncPersonal")
	private Object lastSyncPersonal;

	@SerializedName("expDate")
	private String expDate;

	@SerializedName("wallBalPersonal")
	private String wallBalPersonal;

	@SerializedName("lastSyncTransit")
	private Object lastSyncTransit;

	public String getProductCode(){
		return productCode;
	}

	public List<CardAccountDetailsItem> getCardAccountDetails(){
		return cardAccountDetails;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setCardAccountDetails(List<CardAccountDetailsItem> cardAccountDetails) {
		this.cardAccountDetails = cardAccountDetails;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCardActivDate(String cardActivDate) {
		this.cardActivDate = cardActivDate;
	}

	public void setProxyNumber(String proxyNumber) {
		this.proxyNumber = proxyNumber;
	}

	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setCardRefNumber(String cardRefNumber) {
		this.cardRefNumber = cardRefNumber;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public void setWallBalTransit(String wallBalTransit) {
		this.wallBalTransit = wallBalTransit;
	}

	public void setLastSyncPersonal(Object lastSyncPersonal) {
		this.lastSyncPersonal = lastSyncPersonal;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public void setWallBalPersonal(String wallBalPersonal) {
		this.wallBalPersonal = wallBalPersonal;
	}

	public void setLastSyncTransit(Object lastSyncTransit) {
		this.lastSyncTransit = lastSyncTransit;
	}

	public int getId(){
		return id;
	}

	public String getCardActivDate(){
		return cardActivDate;
	}

	public String getProxyNumber(){
		return proxyNumber;
	}

	public String getCardExpiryDate(){
		return cardExpiryDate;
	}

	public String getProductName(){
		return productName;
	}

	public String getCardStatus(){
		return cardStatus;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public String getSid(){
		return sid;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public String getActivityDate(){
		return activityDate;
	}

	public String getWallBalTransit(){
		return wallBalTransit;
	}

	public Object getLastSyncPersonal(){
		return lastSyncPersonal;
	}

	public String getExpDate(){
		return expDate;
	}

	public String getWallBalPersonal(){
		return wallBalPersonal;
	}

	public Object getLastSyncTransit(){
		return lastSyncTransit;
	}
}