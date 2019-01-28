package com.myhopu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Orders {
	private static final long serialVersionUID = 1L;
	
	//订单编号
	private Integer ordersId;
	//用户ID（外键）
	private User user=new User();
	//应付金额
	private double totalPrice;
	//订单提交时间
	private Date submitTime;
	//付款时间
	private Date payTime;
	//订单状态(0购物车，1未提交，2已付款，-1已失效)
	private Integer state;
	//收货信息ID（外键）
	private RecipientInfo recipientInfo;
	//订单明细表
	private List<OrdersTitleBlock> odersTitleBlockList=new ArrayList<OrdersTitleBlock>();
	
	
	public List<OrdersTitleBlock> getOdersTitleBlockList() {
		return odersTitleBlockList;
	}
	public void setOdersTitleBlockList(List<OrdersTitleBlock> odersTitleBlockList) {
		this.odersTitleBlockList = odersTitleBlockList;
	}
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getTotalPrice() {
		for(OrdersTitleBlock o:odersTitleBlockList) {
			totalPrice+=o.getSumPrice();
		}
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public RecipientInfo getRecipientInfo() {
		return recipientInfo;
	}
	public void setRecipientInfo(RecipientInfo recipientInfo) {
		this.recipientInfo = recipientInfo;
	}
	
	
}
