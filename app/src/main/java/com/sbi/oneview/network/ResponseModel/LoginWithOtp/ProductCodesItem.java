package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import com.google.gson.annotations.SerializedName;

public class ProductCodesItem{

	@SerializedName("displayName")
	private Object displayName;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private Object id;

	@SerializedName("type")
	private Object type;

	@SerializedName("value")
	private String value;

	@SerializedName("url")
	private Object url;

	public Object getDisplayName(){
		return displayName;
	}

	public String getName(){
		return name;
	}

	public Object getId(){
		return id;
	}

	public Object getType(){
		return type;
	}

	public String getValue(){
		return value;
	}

	public Object getUrl(){
		return url;
	}
}