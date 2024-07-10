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

	public void setVirtual(Object virtual) {
		this.virtual = virtual;
	}

	public void setPrepaid(Prepaid prepaid) {
		this.prepaid = prepaid;
	}

	public void setTransitStatus(String transitStatus) {
		this.transitStatus = transitStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public void setFtcStatus(String ftcStatus) {
		this.ftcStatus = ftcStatus;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setVirtualStatus(String virtualStatus) {
		this.virtualStatus = virtualStatus;
	}

	public void setPrepaidStatus(String prepaidStatus) {
		this.prepaidStatus = prepaidStatus;
	}

	public void setFtc(Ftc ftc) {
		this.ftc = ftc;
	}

	public void setTransit(Transit transit) {
		this.transit = transit;
	}

	public void setProductCodes(List<ProductCodesItem> productCodes) {
		this.productCodes = productCodes;
	}

	public List<ProductCodesItem> getProductCodes(){
		return productCodes;
	}

}