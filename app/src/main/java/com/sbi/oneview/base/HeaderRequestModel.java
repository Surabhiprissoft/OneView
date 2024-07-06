package com.sbi.oneview.base;

import com.google.gson.annotations.SerializedName;

public class HeaderRequestModel{

	@SerializedName("Authorization")
	private String authorization;

	@SerializedName("Host")
	private String host;

	@SerializedName("Access-Key")
	private String accessKey;

	@SerializedName("Content-Type")
	private String contentType;

	public void setAuthorization(String authorization){
		this.authorization = authorization;
	}

	public String getAuthorization(){
		return authorization;
	}

	public void setHost(String host){
		this.host = host;
	}

	public String getHost(){
		return host;
	}

	public void setAccessKey(String accessKey){
		this.accessKey = accessKey;
	}

	public String getAccessKey(){
		return accessKey;
	}

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}

	@Override
 	public String toString(){
		return 
			"HeaderRequestModel{" + 
			"authorization = '" + authorization + '\'' + 
			",host = '" + host + '\'' + 
			",access-Key = '" + accessKey + '\'' + 
			",content-Type = '" + contentType + '\'' + 
			"}";
		}
}