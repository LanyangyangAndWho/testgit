package com.myhopu.entity;

import java.util.ArrayList;
import java.util.List;

public class OrdersTitleBlock {
	private static final long serialVersionUID = 1L;
	//订单明细编号
	private Integer orderDetailsId;
	//订单ID（外键）
	private Orders orders;
	//库存ID（外键）
	private Sku sku;
	//数量
	private Integer number;
	//折扣
	private Integer discountNum;
	//属性值
	private List<AttrValue> attrValueList=new ArrayList<AttrValue>();
	//小计
	private double sumPrice;
	
	public double getSumPrice() {
		if(discountNum!=null) {
			sumPrice=sku.getPrice()*number*discountNum/10;
		}
		if(sku!=null) {
			sumPrice=sku.getPrice()*number;
		}
		return sumPrice;
	}
	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Integer getDiscountNum() {
		return discountNum;
	}
	public void setDiscountNum(Integer discountNum) {
		this.discountNum = discountNum;
	}
	public List<AttrValue> getAttrValueList() {
		return attrValueList;
	}
	public void setAttrValueList(List<AttrValue> attrValueList) {
		this.attrValueList = attrValueList;
	}
	public Integer getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Sku getSku() {
		return sku;
	}
	public void setSku(Sku sku) {
		this.sku = sku;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
