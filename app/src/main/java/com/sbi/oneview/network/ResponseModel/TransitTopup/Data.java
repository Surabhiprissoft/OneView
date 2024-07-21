package com.sbi.oneview.network.ResponseModel.TransitTopup;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("txndata")
	private String txndata;

	@SerializedName("merchantCode")
	private String merchantCode;

	@SerializedName("message")
	private String message;

	@SerializedName("url")
	private String url;

	@SerializedName("status")
	private String status;

	public void setTxndata(String txndata){
		this.txndata = txndata;
	}

	public String getTxndata(){
		return txndata;
	}

	public void setMerchantCode(String merchantCode){
		this.merchantCode = merchantCode;
	}

	public String getMerchantCode(){
		return merchantCode;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"txndata = '" + txndata + '\'' + 
			",merchantCode = '" + merchantCode + '\'' + 
			",message = '" + message + '\'' + 
			",url = '" + url + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}