package com.myhopu.service;

import java.util.List;
import java.util.Map;

import com.myhopu.entity.Goods;

public interface GoodsService extends BaseService<Goods> {
/*	//查询相似商品
	public List<Goods> selectLikeGoods(Integer goodsTypeId,Integer goodsId);*/
	Map<String, Object>  selecttypeid();
}
