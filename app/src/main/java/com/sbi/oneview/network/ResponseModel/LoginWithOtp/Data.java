package com.sbi.oneview.network.ResponseModel.LoginWithOtp;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("virtualStatus")
	private String virtualStatus;

	@SerializedName("virtual")
	private Object virtual;

	@SerializedName("prepaidStatus")
	private String prepaidStatus;

	@SerializedName("ftc")
	private Object ftc;

	@SerializedName("transit")
	private Transit transit;

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

	public void setVirtualStatus(String virtualStatus){
		this.virtualStatus = virtualStatus;
	}

	public String getVirtualStatus(){
		return virtualStatus;
	}

	public void setVirtual(Object virtual){
		this.virtual = virtual;
	}

	public Object getVirtual(){
		return virtual;
	}

	public void setPrepaidStatus(String prepaidStatus){
		this.prepaidStatus = prepaidStatus;
	}

	public String getPrepaidStatus(){
		return prepaidStatus;
	}

	public void setFtc(Object ftc){
		this.ftc = ftc;
	}

	public Object getFtc(){
		return ftc;
	}

	public void setTransit(Transit transit){
		this.transit = transit;
	}

	public Transit getTransit(){
		return transit;
	}

	public void setPrepaid(Prepaid prepaid){
		this.prepaid = prepaid;
	}

	public Prepaid getPrepaid(){
		return prepaid;
	}

	public void setTransitStatus(String transitStatus){
		this.transitStatus = transitStatus;
	}

	public String getTransitStatus(){
		return transitStatus;
	}

	public void setLoginStatus(String loginStatus){
		this.loginStatus = loginStatus;
	}

	public String getLoginStatus(){
		return loginStatus;
	}

	public void setLogin(boolean login){
		this.login = login;
	}

	public boolean isLogin(){
		return login;
	}

	public void setFtcStatus(String ftcStatus){
		this.ftcStatus = ftcStatus;
	}

	public String getFtcStatus(){
		return ftcStatus;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"virtualStatus = '" + virtualStatus + '\'' + 
			",virtual = '" + virtual + '\'' + 
			",prepaidStatus = '" + prepaidStatus + '\'' + 
			",ftc = '" + ftc + '\'' + 
			",transit = '" + transit + '\'' + 
			",prepaid = '" + prepaid + '\'' + 
			",transitStatus = '" + transitStatus + '\'' + 
			",loginStatus = '" + loginStatus + '\'' + 
			",login = '" + login + '\'' + 
			",ftcStatus = '" + ftcStatus + '\'' + 
			"}";
		}
}