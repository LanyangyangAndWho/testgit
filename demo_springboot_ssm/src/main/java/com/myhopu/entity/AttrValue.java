package com.myhopu.entity;
public class AttrValue {
	//属性值Id
	private Integer attrValueId;
	//属性名ID
	private Integer attrNameId;
	//属性值
	private String attrValue;
	//属性值键值对
	private String skuAttr;
	
	   private Integer skuId;
	   
	public Integer getSkuId() {
		return skuId;
	}
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}
	public Integer getAttrNameId() {
		return attrNameId;
	}
	public void setAttrNameId(Integer attrNameId) {
		this.attrNameId = attrNameId;
	}
	public Integer getAttrValueId() {
		return attrValueId;
	}
	public void setAttrValueId(Integer attrValueId) {
		this.attrValueId = attrValueId;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public String getSkuAttr() {
		skuAttr=this.getAttrNameId().toString()+":"+this.attrValueId.toString();
		return skuAttr;
	}
	public void setSkuAttr(String skuAttr) {
		this.skuAttr = skuAttr;
	}
	
}
