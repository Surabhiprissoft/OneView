package com.sbi.oneview.network.ResponseModel.BlockUnblockCard;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("message")
	private String message;

	@SerializedName("value")
	private Object value;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setValue(Object value){
		this.value = value;
	}

	public Object getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"message = '" + message + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}