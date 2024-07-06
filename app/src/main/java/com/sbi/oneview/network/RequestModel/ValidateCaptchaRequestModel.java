package com.sbi.oneview.network.RequestModel;

import com.google.gson.annotations.SerializedName;

public class ValidateCaptchaRequestModel{

	@SerializedName("id")
	private int id;

	@SerializedName("text")
	private String text;

	@SerializedName("username")
	private String username;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"ValidateCaptchaRequestModel{" + 
			"id = '" + id + '\'' + 
			",text = '" + text + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}