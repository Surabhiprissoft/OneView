package com.sbi.oneview.network.RequestModel.Transit;

import com.google.gson.annotations.SerializedName;

public class TransitRequestHotlistRequestModel{

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("hotlistResaon")
	private String hotlistResaon;

	@SerializedName("action")
	private String action;

	@SerializedName("sId")
	private String sId;

	public void setCardRefNumber(String cardRefNumber){
		this.cardRefNumber = cardRefNumber;
	}

	public String getCardRefNumber(){
		return cardRefNumber;
	}

	public void setHotlistResaon(String hotlistResaon){
		this.hotlistResaon = hotlistResaon;
	}

	public String getHotlistResaon(){
		return hotlistResaon;
	}

	public void setAction(String action){
		this.action = action;
	}

	public String getAction(){
		return action;
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
			"TransitRequestHotlistRequestModel{" + 
			"cardRefNumber = '" + cardRefNumber + '\'' + 
			",hotlistResaon = '" + hotlistResaon + '\'' + 
			",action = '" + action + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}