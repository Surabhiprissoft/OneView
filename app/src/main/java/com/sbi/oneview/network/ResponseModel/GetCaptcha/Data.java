package com.sbi.oneview.network.ResponseModel.GetCaptcha;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("image")
	private String image;

	@SerializedName("id")
	private int id;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"image = '" + image + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}