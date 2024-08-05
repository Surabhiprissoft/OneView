package com.sbi.oneview.network.ResponseModel.InrTopup;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("data")
	private String data;

	@SerializedName("message")
	private String message;

	@SerializedName("value")
	private String value;

	@SerializedName("url")
	private String url;

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",value = '" + value + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}