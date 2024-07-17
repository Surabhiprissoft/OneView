package com.sbi.oneview.network.ResponseModel.TransitStatement;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("totalRecords")
	private String totalRecords;

	@SerializedName("walletBalTransit")
	private String walletBalTransit;

	@SerializedName("statements")
	private List<StatementsItem> statements;

	@SerializedName("walletBalPersonal")
	private String walletBalPersonal;

	public void setTotalRecords(String totalRecords){
		this.totalRecords = totalRecords;
	}

	public String getTotalRecords(){
		return totalRecords;
	}

	public void setWalletBalTransit(String walletBalTransit){
		this.walletBalTransit = walletBalTransit;
	}

	public String getWalletBalTransit(){
		return walletBalTransit;
	}

	public void setStatements(List<StatementsItem> statements){
		this.statements = statements;
	}

	public List<StatementsItem> getStatements(){
		return statements;
	}

	public void setWalletBalPersonal(String walletBalPersonal){
		this.walletBalPersonal = walletBalPersonal;
	}

	public String getWalletBalPersonal(){
		return walletBalPersonal;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"totalRecords = '" + totalRecords + '\'' + 
			",walletBalTransit = '" + walletBalTransit + '\'' + 
			",statements = '" + statements + '\'' + 
			",walletBalPersonal = '" + walletBalPersonal + '\'' + 
			"}";
		}
}