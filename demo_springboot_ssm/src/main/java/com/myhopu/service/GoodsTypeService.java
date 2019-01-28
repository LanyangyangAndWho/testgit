package com.myhopu.service;

import java.util.Map;

import com.myhopu.entity.GoodsType;

public interface GoodsTypeService extends BaseService<GoodsType> {
	Map<String, Object> selectpid();
}
