package com.sbi.oneview.network.ResponseModel.ProcessEform;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("tempCustRefNumber")
	private String tempCustRefNumber;

	@SerializedName("txnId")
	private String txnId;

	public void setTempCustRefNumber(String tempCustRefNumber){
		this.tempCustRefNumber = tempCustRefNumber;
	}

	public String getTempCustRefNumber(){
		return tempCustRefNumber;
	}

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
			"tempCustRefNumber = '" + tempCustRefNumber + '\'' + 
			",txnId = '" + txnId + '\'' + 
			"}";
		}
}