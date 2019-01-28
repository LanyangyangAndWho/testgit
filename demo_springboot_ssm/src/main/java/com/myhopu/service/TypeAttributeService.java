package com.myhopu.service;

import java.util.Map;

import com.myhopu.entity.GoodsTypeAttr;
import com.myhopu.entity.TypeAttribute;

public interface TypeAttributeService extends BaseService<TypeAttribute> {
	Map<String, Object>  selecttypeid();
	Map<String, Object>  selectattr();
	String insert(GoodsTypeAttr t) ;
}
