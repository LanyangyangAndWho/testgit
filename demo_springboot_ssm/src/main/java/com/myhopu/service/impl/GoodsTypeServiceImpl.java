package com.myhopu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myhopu.entity.GoodsType;
import com.myhopu.mapper.GoodsTypeMapper;
import com.myhopu.service.GoodsTypeService;

@Service
@Transactional
public class GoodsTypeServiceImpl extends BaseServiceImpl<GoodsType> implements GoodsTypeService {
	@Autowired
	GoodsTypeMapper goodsTypeMapper;
	@Override
	public Map<String, Object> selectpid() {		
		List list = goodsTypeMapper.selectpid();		
		map.put("data", list);
		return map;
	}
	@Override
	public String insert(GoodsType t) throws Exception {
		if(t.getTypePId() == null) {
			t.setTypePId(0);
		} 	
		return super.insert(t);
	}
	@Override
	public String updateByPrimaryKey(GoodsType t) throws Exception {
		if(t.getTypePId() == null) {
			t.setTypePId(0);
		} 	
		return super.updateByPrimaryKey(t);
	}
}
