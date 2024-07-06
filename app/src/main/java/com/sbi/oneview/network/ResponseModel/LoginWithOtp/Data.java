package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("virtual")
	private Object virtual;

	@SerializedName("prepaid")
	public Prepaid prepaid;

	@SerializedName("transitStatus")
	private String transitStatus;

	@SerializedName("loginStatus")
	private String loginStatus;

	@SerializedName("login")
	private boolean login;

	@SerializedName("ftcStatus")
	private String ftcStatus;

	@SerializedName("sid")
	private String sid;

	@SerializedName("token")
	private String token;

	@SerializedName("virtualStatus")
	private String virtualStatus;

	@SerializedName("prepaidStatus")
	private String prepaidStatus;

	@SerializedName("ftc")
	public Ftc ftc;

	@SerializedName("transit")
	private Transit transit;

	@SerializedName("productCodes")
	private List<ProductCodesItem> productCodes;

	public Object getVirtual(){
		return virtual;
	}

	public Prepaid getPrepaid(){
		return prepaid;
	}

	public String getTransitStatus(){
		return transitStatus;
	}

	public String getLoginStatus(){
		return loginStatus;
	}

	public boolean isLogin(){
		return login;
	}

	public String getFtcStatus(){
		return ftcStatus;
	}

	public String getSid(){
		return sid;
	}

	public String getToken(){
		return token;
	}

	public String getVirtualStatus(){
		return virtualStatus;
	}

	public String getPrepaidStatus(){
		return prepaidStatus;
	}

	public Ftc getFtc(){
		return ftc;
	}

	public Transit getTransit(){
		return transit;
	}

	public List<ProductCodesItem> getProductCodes(){
		return productCodes;
	}
}