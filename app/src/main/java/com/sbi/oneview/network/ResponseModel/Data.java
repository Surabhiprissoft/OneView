package com.sbi.oneview.network.ResponseModel;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("role")
	private int role;

	@SerializedName("address")
	private String address;

	@SerializedName("is_active")
	private boolean isActive;

	@SerializedName("profile_photo")
	private Object profilePhoto;

	@SerializedName("gender")
	private Object gender;

	@SerializedName("center")
	private int center;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("discount")
	private String discount;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("year_of_experience")
	private int yearOfExperience;

	@SerializedName("session_token")
	private String sessionToken;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("phone")
	private Object phone;

	@SerializedName("dob")
	private Object dob;

	@SerializedName("training_type")
	private String trainingType;

	@SerializedName("id")
	private int id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	@SerializedName("is_firsttime")
	private boolean isFirsttime;

	@SerializedName("username")
	private String username;

	@SerializedName("is_approve")
	private boolean isApprove;

	public void setRole(int role){
		this.role = role;
	}

	public int getRole(){
		return role;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setProfilePhoto(Object profilePhoto){
		this.profilePhoto = profilePhoto;
	}

	public Object getProfilePhoto(){
		return profilePhoto;
	}

	public void setGender(Object gender){
		this.gender = gender;
	}

	public Object getGender(){
		return gender;
	}

	public void setCenter(int center){
		this.center = center;
	}

	public int getCenter(){
		return center;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setYearOfExperience(int yearOfExperience){
		this.yearOfExperience = yearOfExperience;
	}

	public int getYearOfExperience(){
		return yearOfExperience;
	}

	public void setSessionToken(String sessionToken){
		this.sessionToken = sessionToken;
	}

	public String getSessionToken(){
		return sessionToken;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPhone(Object phone){
		this.phone = phone;
	}

	public Object getPhone(){
		return phone;
	}

	public void setDob(Object dob){
		this.dob = dob;
	}

	public Object getDob(){
		return dob;
	}

	public void setTrainingType(String trainingType){
		this.trainingType = trainingType;
	}

	public String getTrainingType(){
		return trainingType;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setIsFirsttime(boolean isFirsttime){
		this.isFirsttime = isFirsttime;
	}

	public boolean isIsFirsttime(){
		return isFirsttime;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setIsApprove(boolean isApprove){
		this.isApprove = isApprove;
	}

	public boolean isIsApprove(){
		return isApprove;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"role = '" + role + '\'' + 
			",address = '" + address + '\'' + 
			",is_active = '" + isActive + '\'' + 
			",profile_photo = '" + profilePhoto + '\'' + 
			",gender = '" + gender + '\'' + 
			",center = '" + center + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",discount = '" + discount + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",year_of_experience = '" + yearOfExperience + '\'' + 
			",session_token = '" + sessionToken + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",phone = '" + phone + '\'' + 
			",dob = '" + dob + '\'' + 
			",training_type = '" + trainingType + '\'' + 
			",id = '" + id + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' + 
			",is_firsttime = '" + isFirsttime + '\'' + 
			",username = '" + username + '\'' + 
			",is_approve = '" + isApprove + '\'' + 
			"}";
		}
}