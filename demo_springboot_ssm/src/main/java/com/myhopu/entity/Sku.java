package com.myhopu.entity;

public class Sku {
	private static final long serialVersionUID = 1L;
	//库存ID
	private Integer skuId;
	//sku属性键值对
	private String skuAttrs;
	//价格
	private double price;
	//折后价
	private double activitiPrice;
	//库存
	private Integer store;
	//商品图片默认资源路径
	private String photoUrl;
	//商品编号
	private Goods goods;
	private Integer skuSales;
    private String temp;
    
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public Integer getSkuSales() {
		return skuSales;
	}
	public void setSkuSales(Integer skuSales) {
		this.skuSales = skuSales;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public String getSkuAttrs() {
		return skuAttrs;
	}
	public void setSkuAttrs(String skuAttrs) {
		this.skuAttrs = skuAttrs;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getActivitiPrice() {
		return activitiPrice;
	}
	public void setActivitiPrice(double activitiPrice) {
		this.activitiPrice = activitiPrice;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
}
