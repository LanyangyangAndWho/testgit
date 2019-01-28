package com.myhopu.entity;

import java.util.ArrayList;
import java.util.List;

public class AttrName {
	private static final long serialVersionUID = 1L;
	//属性名编号
	private Integer attrNameId;
	//属性名
	private String attrName;
	//属性值信息
	private List<AttrValue> attrValueList=new ArrayList<AttrValue>();
	public Integer getAttrNameId() {
		return attrNameId;
	}
	public void setAttrNameId(Integer attrNameId) {
		this.attrNameId = attrNameId;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public List<AttrValue> getAttrValueList() {
		return attrValueList;
	}
	public void setAttrValueList(List<AttrValue> attrValueList) {
		this.attrValueList = attrValueList;
	}
	
}
