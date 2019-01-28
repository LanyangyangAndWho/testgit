package com.myhopu.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	//用户编号
	private Integer userId;
	//账户名称
	private String userName;
	//手机号
	private String userPwd;
	private String phoneNumber;
	//邮寄信息
	private List<RecipientInfo> recipientInfoList=new ArrayList<RecipientInfo>();
	
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public List<RecipientInfo> getRecipientInfoList() {
		return recipientInfoList;
	}
	public void setRecipientInfoList(List<RecipientInfo> recipientInfoList) {
		this.recipientInfoList = recipientInfoList;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
