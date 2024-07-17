package com.sbi.oneview.network.ResponseModel.ValidateEform;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("txnId")
	private String txnId;

	public void setTxnId(String txnId){
		this.txnId = txnId;
	}

	public String getTxnId(){
		return txnId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"txnId = '" + txnId + '\'' + 
			"}";
		}
}