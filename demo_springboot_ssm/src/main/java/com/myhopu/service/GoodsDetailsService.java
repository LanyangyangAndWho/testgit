package com.myhopu.service;


import java.util.Map;

public interface GoodsDetailsService  {
	//得到一个商品的详细信息表
	public Map<String,Object> selectAllDetail(Integer goodsId,Integer userId);
	//添加收藏
	public void addCollectGoods(Integer userId,Integer goodsId);
	//取消收藏
	public void delCollectGoods(Integer userId,Integer goodsId);
}
