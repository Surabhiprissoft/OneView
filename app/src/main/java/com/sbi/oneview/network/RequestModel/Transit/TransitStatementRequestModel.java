package com.sbi.oneview.network.RequestModel.Transit;

import com.google.gson.annotations.SerializedName;

public class TransitStatementRequestModel{

	@SerializedName("fromDate")
	private String fromDate;

	@SerializedName("cardRefNumber")
	private String cardRefNumber;

	@SerializedName("productCode")
	private String productCode;

	@SerializedName("pageNo")
	private String pageNo;

	@SerializedName("toDate")
	private String toDate;

	@SerializedName("sId")
	private String sId;

	public void setFromDate(String fromDate){
		this.fromDate = fromDate;
	}

	public String getFromDate(){
		return fromDate;
	}

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

	public void setPageNo(String pageNo){
		this.pageNo = pageNo;
	}

	public String getPageNo(){
		return pageNo;
	}

	public void setToDate(String toDate){
		this.toDate = toDate;
	}

	public String getToDate(){
		return toDate;
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
			"TransitStatementRequestModel{" + 
			"fromDate = '" + fromDate + '\'' + 
			",cardRefNumber = '" + cardRefNumber + '\'' + 
			",productCode = '" + productCode + '\'' + 
			",pageNo = '" + pageNo + '\'' + 
			",toDate = '" + toDate + '\'' + 
			",sId = '" + sId + '\'' + 
			"}";
		}
}