package com.myhopu.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Goods {
	private static final long serialVersionUID = 1L;
	
	//商品编号
	private Integer goodsId;
	//商品类别
	private GoodsType goodsType =new GoodsType();
	//商品名称
	private String goodsName;
	//价格
	private double price;
	//折扣
	private Integer discountNum;
	//折后价
	private double activitiPrice;
	//上架时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // 后台发送到前台的格式
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 前台发送到后台的格式
	private Date addTimes;
	//SUP销量
	private Integer supSales;
	//评论数
	private Integer commentNum;
	//商品图片默认资源路径
	private String defaultPhotoUrl;
	//商品详细图片
	private String detailsPhotoUrl;
	//是否收藏
	private int collect;
	//相似商品信息
	private List<Goods> goodsLikeList=new ArrayList<Goods>();
	//sku信息
	private List<Sku> skuList=new ArrayList<Sku>();
	//attr信息
	private List<AttrName> attrList=new ArrayList<AttrName>();
	//sort
	private String sort;
	//count
	private Integer count;

	
	public Integer getDiscountNum() {
		return discountNum;
	}
	public void setDiscountNum(Integer discountNum) {
		this.discountNum = discountNum;
	}
	public double getActivitiPrice() {
		if(discountNum!=null) {
			activitiPrice=price*discountNum/10;
		}
		return activitiPrice;
	}
	public void setActivitiPrice(double activitiPrice) {
		this.activitiPrice = activitiPrice;
	}

	public String getDetailsPhotoUrl() {
		return detailsPhotoUrl;
	}
	public void setDetailsPhotoUrl(String detailsPhotoUrl) {
		this.detailsPhotoUrl = detailsPhotoUrl;
	}
	public int getCollect() {
		return collect;
	}
	public void setCollect(int collect) {
		this.collect = collect;
	}
	public List<Goods> getGoodsLikeList() {
		return goodsLikeList;
	}
	public void setGoodsLikeList(List<Goods> goodsLikeList) {
		this.goodsLikeList = goodsLikeList;
	}
	public List<Sku> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}
	public List<AttrName> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<AttrName> attrList) {
		this.attrList = attrList;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public GoodsType getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getAddTimes() {
		return addTimes;
	}
	public void setAddTimes(Date addTimes) {
		this.addTimes = addTimes;
	}
	public Integer getSupSales() {
		return supSales;
	}
	public void setSupSales(Integer supSales) {
		this.supSales = supSales;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public String getDefaultPhotoUrl() {
		return defaultPhotoUrl;
	}
	public void setDefaultPhotoUrl(String defaultPhotoUrl) {
		this.defaultPhotoUrl = defaultPhotoUrl;
	}
	
}
