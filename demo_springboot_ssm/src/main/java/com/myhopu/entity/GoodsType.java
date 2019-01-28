package com.myhopu.entity;

public class GoodsType {
	private static final long serialVersionUID = 1L;
	
	//商品类别编号
	private Integer typeId;
	//商品父类别编号
	private Integer typePId;
	//商品类别名称
	private String typeName;
	private Integer sortkey;
	
	public Integer getSortkey() {
		return sortkey;
	}
	public void setSortkey(Integer sortkey) {
		this.sortkey = sortkey;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getTypePId() {
		return typePId;
	}
	public void setTypePId(Integer typePId) {
		this.typePId = typePId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
