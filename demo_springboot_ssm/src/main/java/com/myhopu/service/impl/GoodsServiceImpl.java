package com.myhopu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myhopu.entity.Goods;
import com.myhopu.mapper.GoodsMapper;
import com.myhopu.service.GoodsService;

@Service
@Transactional
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Override
	public Map<String, Object> selecttypeid() {
		// TODO Auto-generated method stub
		List list = goodsMapper.selectTypeId();		
		map.put("data", list);
		return map;
	}
/*	
	//查询相似商品
	@Override
	public List<Goods> selectLikeGoods(Integer goodsTypeId, Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsMapper.selectLikeGoods(goodsTypeId, goodsId);
	}*/
	
}
