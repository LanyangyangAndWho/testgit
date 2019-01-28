package com.myhopu.entity;

public class RecipientInfo {
	private static final long serialVersionUID = 1L;
	//收货信息编号
	private Integer recipientId;
	//收货地址
	private String recipientAddress;
	//收货人
	private String recipientOfficer;
	//手机号
	private String phone;
	//是否默认（0，1是默认）
	private Integer isDefault;
	public Integer getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(Integer recipientId) {
		this.recipientId = recipientId;
	}
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	public String getRecipientOfficer() {
		return recipientOfficer;
	}
	public void setRecipientOfficer(String recipientOfficer) {
		this.recipientOfficer = recipientOfficer;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
}
